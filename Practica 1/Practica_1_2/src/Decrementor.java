
public class Decrementor extends Thread{
	
	DC dc;

	public Decrementor(DC dc) {
		this.dc = dc;
	}
	
	public void run() {
		dc.x--;
	}
}
