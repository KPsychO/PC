package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Client {

	private static String address;
	private static int port;
	private static String username;
	private static Socket s;
	private static ArrayList<String> files;
	private static ServerListener serverListener;
	static Scanner in = new Scanner(System.in);
	private static String file;
	static boolean exit = false;

	public static void main(String[] args) throws InterruptedException {

		if (args.length != 2) {
			System.out.println("Provided arguments are not correct.");
			System.out.println("Provided args must be: (string) hostname, (int) port");
			return;
		}

		address = args[0];
		port = Integer.parseInt(args[1]);

		System.out.print("Enter your username: ");
		username = in.nextLine();
		files = new ArrayList<String>();

		loadFiles();

		try {

			s = new Socket(address, port);

			serverListener = new ServerListener(s);
			serverListener.start();

			newConnection();

			while (!exit) {

				Thread.sleep(200);
				menu();

			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		in.close();

	}

	private static void newConnection() {

		System.out.println("Stablishing connection with the server...");

		if (serverListener.newconnection(username)) {

			System.out.println("Connection stablished:");
			System.out.println("	Host:		" + address);
			System.out.println("	Port:		" + port);
			System.out.println("	Username:	" + username);
			System.out.println("----------------------------------------");
		}

	}

	protected static void getFile(int p) {

		try {
			System.out.println("Creating downloader...");
			(new Downloader(new Socket(address, p), file)).start();
			;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void loadFiles() {

		System.out.println("Files in this user folder:");

		try {
			Path dirPath = Paths.get("./Files/" + username + "/");
			try (DirectoryStream<Path> dirPaths = Files.newDirectoryStream(dirPath)) {
				for (Path file : dirPaths) {
					System.out.println(file.getFileName().toString());
					files.add(file.getFileName().toString());
				}
			}
		} catch (Exception e) {
			System.out.println("User " + username + " not found in the system...");
		}

		System.out.println("----------------------------------------");

	}

	private static void menu() {

		String opt = "";

		System.out.println("	1. List users");
		System.out.println("	2. Request file");
		System.out.println("	3. Close connection");
		System.out.println("----------------------------------------");

		System.out.println("Enter your choice: ");

		opt = in.nextLine();

		if (opt.equals("1") || opt.equals("2") || opt.equals("3")) {

			switch (Integer.parseInt(opt)) {

			case 1:
				serverListener.listUsers();
				break;

			case 2:
				System.out.print("Input the name of the requested file: ");
				file = in.nextLine();
				serverListener.requestFile(file);
				break;

			case 3:
				serverListener.closeCon();
				break;

			default:
				System.out.println("[ERROR]: Undefined option...");
				break;

			}
		} else
			System.out.println("[ERROR]: Undefined option...");
	}

	protected static void exit() {
		exit = true;
	}

	protected static void printUsers(HashMap<String, ArrayList<String>> clientFiles) {
		for (Entry<String, ArrayList<String>> entry : clientFiles.entrySet()) {
			System.out.println(entry.getKey() + ":");

			int i = 1;
			for (String file : entry.getValue()) {
				System.out.println("	" + i + ": " + file);
				i++;
			}

		}
		System.out.println("----------------------------------------");
	}

	public static String getUsername() {
		return username;
	}

}
