public class Main {

	private static int N = 10;

	public static void main(String args[]) {

		MyThread[] threads = new MyThread[N];

		System.out.println("Iniciando hilos...");

		for (int i = 0; i < N; i++) {

			threads[i] = new MyThread(i, (int) (Math.random()*500+10));
			threads[i].start();

		}
		
		for (int i = 0; i < N; i++) {

			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}	