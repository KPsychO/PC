package Parte1;

import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {

	private Monitor monitor;
	public int id;
	private int rate; // Number of productions each execution
	private int productions; // number of times to produce items

	public Producer(Monitor monitor, int i) {
		this.productions = 10;
		this.monitor = monitor;
		this.id = i;
//		this.rate = 3;
		this.rate = ThreadLocalRandom.current().nextInt(1, (monitor.getLen()/3) + 1);
	}

	@Override
	public void run() {
		int i = 0;

		while (i < this.productions) {
			Product products[] = new Product[this.rate];
			for (int j = 0; j < this.rate; j++) {
				monitor.setpID(monitor.getpID() + 1);
				Product aux = new Product(monitor.getpID());
				products[j] = aux;
			}

			monitor.produce(products, rate, this.id);

			i++;

		}
	}

}
