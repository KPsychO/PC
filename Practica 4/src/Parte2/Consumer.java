package Parte2;

import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread {

	private Monitor monitor;
	private int consumtions;
	private int id;
	private int rate;

	public Consumer(Monitor monitor, int i) {
		this.monitor = monitor;
		this.consumtions = 10;
		this.id = i;
//		this.rate = 3;
		this.rate = ThreadLocalRandom.current().nextInt(1, (monitor.getLen()/3) + 1);
	}

	@Override
	public void run() {
		int i = 0;
		while (i < this.consumtions) {
			
			@SuppressWarnings("unused")
			Product[] prod = monitor.consume(this.id, this.rate);

			i++;
		}
	}

}
