package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientTableData {

	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	public ClientTableData(ObjectInputStream ois, ObjectOutputStream oos) {

		this.setOutputStream(oos);
		this.setInputStream(ois);

	}

	public ObjectInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ObjectInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}

}
