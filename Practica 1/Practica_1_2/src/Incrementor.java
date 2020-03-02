
public class Incrementor extends Thread{
	
	DC dc;

	public Incrementor(DC dc) {
		this.dc = dc;
	}
	
	public void run() {
		dc.x++;
	}
}
