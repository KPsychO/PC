package Parte2.Bakery;

public class Decrementor extends Thread {
	Bakery lock;
	Var elem;
	int id;
	int n;
	
	public Decrementor (int i, int n, Bakery lock, Var elem) {
		this.elem = elem;
		this.lock = lock;
		this.id = i;
		this.n = n;
	}
	
	@Override
	public void run () {
		for (int i =0; i<50; i++) {
			lock.takeLock(id);
//			System.out.println("lock taken by" + id);
			elem.dec();
			lock.releaseLock(id);
//			System.out.println("lock released by" + id);
		}
	}
}