package Parte2.Bakery;

public class Bakery {
	volatile int turno[];
	volatile int n;
	
	public Bakery(int n) {
		turno = new int[n];
		this.n = n;
		for (int i = 0; i < n; i++) {
			turno[i] = -1;
		}
			
	}
	
	public void takeLock (int id) {
		
		int m = 0;
		for (int j = 0; j < n; j++) {
			if (turno[j] > m)
				m = turno[j];
		}
		
		turno[id] = m+1;
		turno = turno;
		
		for (int j = 0; j < n; j++) {
			if (j != id)
				while (((turno[id] == turno[j]) && (id > j) || (turno[id] > turno[j])) && (turno[j] != -1));
		}
		
	}
	
	public void releaseLock (int id) {
		
		turno[id] = -1;
		turno = turno;
		
	}
	
}