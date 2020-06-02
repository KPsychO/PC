package Msg;

import java.io.Serializable;

public class MyMessage implements Serializable {

	private static final long serialVersionUID = 7526472297652776143L;
	private String type;
	private Object data;

	public MyMessage(String type) {
		this.type = type;
		this.data = null;
	}

	public MyMessage(String type, Object data) {
		this.type = type;
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public Object getData() {
		return data;
	}

}
