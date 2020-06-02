package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import Msg.MyMessage;

public class ServerListener extends Thread {

	private ObjectInputStream inputStream;
	private static ObjectOutputStream outputStream;
	private boolean connected;

	public ServerListener(Socket s) throws IOException {

		outputStream = new ObjectOutputStream(s.getOutputStream());
		this.inputStream = new ObjectInputStream(s.getInputStream());
		this.connected = true;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {

		System.out.println("Executing ServerListener...");

		while (connected) {

			try {
				MyMessage msg = (MyMessage) this.inputStream.readObject();
				switch (msg.getType()) {
				case "newconACK":
					System.out.println("Connection succesful.");
					break;
				case "listusersACK":
					System.out.println("Current users on the system:\n");
					Client.printUsers((HashMap<String, ArrayList<String>>) msg.getData());
					break;
				case "consuccess":
					Client.getFile(Integer.parseUnsignedInt(msg.getData().toString()));
					break;
				case "conerror":
					System.out.println(msg.getData());
					break;
				case "openss":
					int cport = Integer.parseInt(msg.getData().toString());
					try {
						System.out.println("Create ServerSocket on port: " + cport);
						@SuppressWarnings("resource")
						ServerSocket ss = new ServerSocket(cport);
						while (true) {
							System.out.println("Waiting for clients to connect");
							Socket s = ss.accept();
							(new ClientListener(s)).start();

						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					System.out.println("Command not recognised...");
					break;
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

	protected boolean newconnection(String username) {

		System.out.println("New Connection requested...");
		try {
			outputStream.writeObject(new MyMessage("newcon", username));
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;

	}

	protected void listUsers() {

		try {

			outputStream.writeObject(new MyMessage("listusers"));
			outputStream.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void requestFile(String file) {

		System.out.println("Requesting file " + file + "...");

		try {
			outputStream.writeObject(new MyMessage("reqfile", file));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void closeCon() {

		this.connected = false;

		System.out.println("Closing connection...");

		try {
			outputStream.writeObject(new MyMessage("closecon"));
			outputStream.flush();
			inputStream.close();
			outputStream.close();
			Client.exit();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected static void notifyFileRecieved(String fileName) {
		try {
			outputStream.writeObject(new MyMessage("filerec", fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}