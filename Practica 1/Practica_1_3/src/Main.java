public class Main {

	private static int N = 25;

	public static void main(String args[]) {

		MyThread[] ts = new MyThread[N];
		Matrix a = new Matrix(N);
		Matrix b = new Matrix(N);
		
		int c[][] = new int[N][N];

		System.out.println("Iniciando hilos...");

		for (int i = 0; i < N; i++) {
			
			ts[i] = new MyThread(N, i, a.m, b.m, c);
			ts[i].start();

		}
		
		for (int i = 0; i < N; i++) {

			try {
				ts[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				System.out.print(c[i][j] + "	");
				
			}
			System.out.print("\n");
		}
		
		

	}

}	

class Matrix {
	
	public int[][] m;
	
	public Matrix (int N) {
		
		m = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				m[i][j] = (int) (Math.random()*100+0);
				
			}
		}

	}
	
}