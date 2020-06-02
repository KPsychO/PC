package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Msg.MyMessage;

public class ClientListener extends Thread {

	Socket s;
	private String username;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private ClientFilesMonitor clientFilesMonitor;
	private ClientSocketsMonitor clientSocketsMonitor;

	public ClientListener(Socket s, ClientFilesMonitor clientFilesMonitor, ClientSocketsMonitor clientSocketsMonitor)
			throws IOException {

		this.s = s;
		this.clientFilesMonitor = clientFilesMonitor;
		this.clientSocketsMonitor = clientSocketsMonitor;
		this.outputStream = new ObjectOutputStream(s.getOutputStream());
		this.inputStream = new ObjectInputStream(s.getInputStream());

	}

	@Override
	public void run() {

		boolean exit = false;

		System.out.println("Executing ClientListener...");

		while (!exit) {
			try {
				while (!this.s.isInputShutdown()) {

					MyMessage msg = (MyMessage) this.inputStream.readObject();

					switch (msg.getType()) {

					case "newcon":
						System.out.println("New Connection requested...");
						username = (String) msg.getData();
						Server.addClientSockets(username, inputStream, outputStream, clientSocketsMonitor);
						this.outputStream.writeObject(new MyMessage("newconACK"));
						this.outputStream.flush();
						System.out.println("New Connection completed (" + username + ")...");
						break;

					case "listusers":
						System.out.println("List users requested by " + username + "...");
						this.outputStream
								.writeObject(new MyMessage("listusersACK", Server.getUsers(clientFilesMonitor)));

						break;

					case "closecon":
						System.out.print("User " + username + " disconecting...");
						exit = true;
						this.inputStream.close();
						this.outputStream.close();
						this.s.close();
						break;

					case "reqfile":
						System.out.println("User " + username + " requested file...");
						String file = msg.getData().toString();
						int port = Server.searchFile(file, clientFilesMonitor, clientSocketsMonitor);
						if (port != -1) {
							this.outputStream.writeObject(new MyMessage("consuccess", port));
						} else {
							this.outputStream.writeObject(new MyMessage("conerror", "file not found"));
						}
						break;

					case "filerec":
						Server.addFileToUser(username, msg.getData().toString(), clientFilesMonitor);
						break;

					default:
						System.out.println("[ERROR]: Unrecognised message.");
						break;
					}
				}

			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		Server.saveFilesData(clientFilesMonitor);

	}
}