package Parte2.TieBreaker;

public class Tiebreaker {
	volatile int in[];
	volatile int last[];
	volatile int n;
	public Tiebreaker(int n) {
		in = new int[n];
		last = new int[n];
		this.n = n;
		for (int i = 0; i < n; i++) {
			in[i] = -1;
			last[i] = -1;
		}
			
	}
	
	public void takeLock (int id) {
		int k;
		for (int j = 0; j < n; j++) {
			in[id] = j;
			in = in;
			last[j] = id;
			last = last;
			for (k = 0; k<n; k++) {
				if (k!=id)
					while(in[k] >= in[id] && last[j] == id);
			}
		}
	}
	
	public void releaseLock (int id) {
		this.in[id] = -1;
	}
	
	
}