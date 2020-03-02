
public class MyThread extends Thread{
	
	int i;
	int N;
	int[][] a;
	int[][] b;
	int[][] c;
	
	public MyThread(int N, int i, int[][] a, int[][] b, int[][] c) {
		this.i = i;
		this.N = N;	
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public void run() {
		
		for (int j = 0; j < N; j++) {
			
			c[i][j] = 0;
			for(int i = 0; i < N; i++) {
				
				c[i][j] = c[i][j] + a[i][j]*b[i][j];
				
			}
			
		}
		
	}

}
