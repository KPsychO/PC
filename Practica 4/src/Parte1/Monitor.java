package Parte1;

public class Monitor {

	private Product[] buffer;
	private int buff_len;
	private int pID;
	private int front;
	private int rear;
	private int c;
	private volatile Product[] cons;

	public Monitor(int buff_len, int rate) {
		this.buffer = new Product[buff_len];
		this.cons = new Product[rate];
		this.buff_len = buff_len;
		this.pID = 0;
		this.front = 0;
		this.rear = 0;
		this.c = 0;
	}

	public synchronized void produce(Product[] prods, int rate, int pID) {

		while (c > (buff_len - rate)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < rate; i++) {
			buffer[rear] = prods[i];
			rear = (rear + 1) % buff_len;
			System.out.println("Producer " + pID + " produced item " + prods[i].getId());
			this.c++;
		}

		notifyAll();

	}

	public synchronized Product[] consume(int cID, int rate) {
		Product[] ret = this.cons;

		while (c <= (buff_len - rate)) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < rate; i++) {
			this.cons[i] = null;
			ret[i] = buffer[front];
			front = (front + 1) % buff_len;
			System.out.println("Consumer " + cID + " consumed item " + ret[i].getId());
			this.c--;
		}
		notifyAll();
		return ret;
	}

	public int getLen() {
		return this.buff_len;
	}

	public int getpID() {
		return this.pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

}
