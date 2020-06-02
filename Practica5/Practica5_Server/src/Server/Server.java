package Server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

import Msg.MyMessage;

public class Server {

	private static int port;
	static String filename;

	static ServerSocket serverSocket;
	private static int nextPort;

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Provided arguments are not correct.");
			System.out.println("Provided args must be: (int) port, (string) datafile");
			return;
		}

		port = Integer.parseInt(args[0]);
		filename = args[1];
		nextPort = port;

		System.out.println("Server initiating on port: " + port);

		ClientSocketsMonitor clientSocketsMonitor = new ClientSocketsMonitor();
		ClientFilesMonitor clientFilesMonitor = new ClientFilesMonitor();

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

		loadClientData(clientFilesMonitor);

		while (true) {
			System.out.println("Waiting for users to connect...");
			try {
				Socket s = serverSocket.accept();
				(new ClientListener(s, clientFilesMonitor, clientSocketsMonitor)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	private static void loadClientData(ClientFilesMonitor clientFilesMonitor) {

		System.out.println("Loading client data...");

		try {

			JSONArray data = new JSONArray(new JSONTokener(new FileReader(filename)));

			for (Object o : data) {

				JSONObject item = (JSONObject) o;
				JSONArray aux = (JSONArray) item.get("files");

				ArrayList<String> l = new ArrayList<String>();

				for (Object o2 : aux) {
					l.add((String) o2);
				}

				clientFilesMonitor.addClientFiles((String) item.get("user"), l);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected static void addClientSockets(String cli, ObjectInputStream ois, ObjectOutputStream oos,
			ClientSocketsMonitor clientSocketsMonitor) {
		clientSocketsMonitor.addClientSockets(cli, ois, oos);
	}

	protected static void addClientFiles(String cli, ArrayList<String> l, ClientFilesMonitor clientFilesMonitor) {
		clientFilesMonitor.addClientFiles(cli, l);
	}

	protected static HashMap<String, ArrayList<String>> getUsers(ClientFilesMonitor clientFilesMonitor) {
		return clientFilesMonitor.getFiles();

	}

	protected static int searchFile(String file, ClientFilesMonitor clientFilesMonitor,
			ClientSocketsMonitor clientSocketsMonitor) {
		String cli = clientFilesMonitor.searchFile(file);

		if (!cli.equals("")) {
			nextPort++;
			try {
				clientSocketsMonitor.getOutputStream(cli).writeObject(new MyMessage("openss", nextPort));
				clientSocketsMonitor.getOutputStream(cli).flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return nextPort;
		}

		return -1;

	}

	protected static void saveFilesData(ClientFilesMonitor clientFilesMonitor) {
		HashMap<String, ArrayList<String>> files = getUsers(clientFilesMonitor);
		ArrayList<String> data = new ArrayList<String>();
		for (Entry<String, ArrayList<String>> entry : files.entrySet()) {
			JSONObject en = new JSONObject();
			en.append("user", entry.getKey());
			JSONArray ar = new JSONArray();
			for (String i : entry.getValue()) {
				ar.put(i);
			}
			en.append("files", ar);
			data.add(en.toString().replace("[\"", "\"").replace("\"]", "\""));
		}

		try {
			FileWriter file = new FileWriter(filename);
			file.write(data.toString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected static void addFileToUser(String username, String fileName, ClientFilesMonitor clientFilesMonitor) {
		clientFilesMonitor.addFileToClient(username, fileName);
	}

}
