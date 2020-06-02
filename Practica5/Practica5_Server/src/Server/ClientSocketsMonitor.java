package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class ClientSocketsMonitor {

	// TODO

	private static HashMap<String, ClientTableData> clientSockets;

	public ClientSocketsMonitor() {
		clientSockets = new HashMap<String, ClientTableData>();
	}

	protected synchronized void addClientSockets(String cli, ObjectInputStream ois, ObjectOutputStream oos) {
		if (!clientSockets.containsKey(cli))
			clientSockets.put(cli, new ClientTableData(ois, oos));
	}

	protected synchronized ObjectOutputStream getOutputStream(String cli) {
		System.out.println(cli);
		return clientSockets.get(cli).getOutputStream();
	}

	protected synchronized HashMap<String, ClientTableData> getSockets() {
		return clientSockets;
	}

}
