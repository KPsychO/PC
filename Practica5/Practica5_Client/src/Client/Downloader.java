package Client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Msg.MyMessage;

public class Downloader extends Thread {

	ObjectOutputStream oos;
	ObjectInputStream ois;
	String fileName;

	public Downloader(Socket sc, String file) throws IOException {
		this.oos = new ObjectOutputStream(sc.getOutputStream());
		this.ois = new ObjectInputStream(sc.getInputStream());
		this.fileName = file;
	}

	@Override
	public void run() {
		System.out.println("Running downloader...");
		try {
			oos.writeObject(new MyMessage("reqfile", fileName));
			MyMessage res = (MyMessage) ois.readObject();
			switch (res.getType()) {
			case "reqfileACK":
				ImageIcon icon = (ImageIcon) (res.getData());
				BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
						BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.createGraphics();
				icon.paintIcon(null, g, 0, 0);
				g.dispose();
				File path = new File("./Files/" + Client.getUsername() + "/" + fileName);

				ImageIO.write(bi, "jpeg", path);

				System.out.println(fileName + " recieved...");
				ServerListener.notifyFileRecieved(fileName);

				sleep(2000);

				this.ois.close();
				this.oos.close();

				break;
			default:
				System.out.println("[ERROR]: Response not recognized...");
				break;
			}
		} catch (IOException | ClassNotFoundException | InterruptedException e) {
			e.printStackTrace();
		}

	}

}
