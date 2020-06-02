package Client;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Msg.MyMessage;

public class ClientListener extends Thread {

	private int port;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket s;

	public ClientListener(Socket s) throws IOException {

		this.s = s;
		this.port = s.getLocalPort();
		this.oos = new ObjectOutputStream(s.getOutputStream());
		this.ois = new ObjectInputStream(s.getInputStream());

	}

	@Override
	public void run() {

		System.out.println("ServerSocket client created on port: " + this.port);
		boolean connected = true;
		while (connected) {

			try {
				MyMessage msg = (MyMessage) this.ois.readObject();
				System.out.println(msg.getType());
				String fileName = msg.getData().toString();
				switch (msg.getType()) {
				case "reqfile":

					File f = new File("./Files/" + Client.getUsername() + "/" + fileName);
					Image file = ImageIO.read(f);

					this.oos.writeObject(new MyMessage("reqfileACK", new ImageIcon(file)));

					sleep(2000);

					connected = false;

					break;

				default:
					System.out.println("Command not recognised...");
					break;
				}

			} catch (IOException | ClassNotFoundException | InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			this.ois.close();
			this.oos.close();
			this.s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
