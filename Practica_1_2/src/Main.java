public class Main {

	private static int N = 1000;

	public static void main(String args[]) {

		Incrementor[] incs = new Incrementor[N];
		Decrementor[] decs = new Decrementor[N];
		
		DC dc = new DC(0);

		System.out.println("Iniciando hilos...");

		for (int i = 0; i < N; i++) {

			incs[i] = new Incrementor(dc);
			decs[i] = new Decrementor(dc);
			incs[i].start();
			decs[i].start();

		}
		
		for (int i = 0; i < N; i++) {

			try {
				incs[i].join();
				decs[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		System.out.println(dc.x);

	}

}	

class DC {
	
	public int x;
	
	public DC (int x) {
		this.x = x;
	}
	
}