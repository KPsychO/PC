package Server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class ClientFilesMonitor {

	private static HashMap<String, ArrayList<String>> clientFiles;

	public ClientFilesMonitor() {
		clientFiles = new HashMap<String, ArrayList<String>>();
	}

	protected synchronized void addClientFiles(String cli, ArrayList<String> l) {
		if (!clientFiles.containsKey(cli))
			clientFiles.put(cli, l);
	}

	protected synchronized HashMap<String, ArrayList<String>> getFiles() {
		return clientFiles;
	}

	protected synchronized String searchFile(String file) {
		String cli = "";
		ArrayList<String> l = null;
		for (Entry<String, ArrayList<String>> entry : clientFiles.entrySet()) {
			l = entry.getValue();
			for (String i : l) {
				if (i.equals(file)) {
					cli = entry.getKey();
					break;
				}
			}
		}
		return cli;
	}

	public synchronized void addFileToClient(String username, String filename) {
		ArrayList<String> ar = clientFiles.get(username);
		ar.add(filename);
		clientFiles.replace(username, ar);
	}

}
