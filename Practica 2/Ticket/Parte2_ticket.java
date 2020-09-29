package Parte2.Ticket;

public class Parte2_ticket {
	
	public static void main(String[] args) {
		int n = 100;
		Var elem = new Var();
		LockTicketMax lock = new LockTicketMax(2*n, n);
		Thread[] hilos = new Thread[2*n];
		for(int i = 0; i < 2*n; i+=2) {
			hilos[i] = new Incrementor(i, 2*n, lock, elem);
			hilos[i+1] = new Decrementor(i+1, 2*n, lock, elem);
		}
		
		System.out.println("LockTicketMax");
		System.out.println("Inicio de los hilos, elem value:" + elem.x);
		
		for(int i = 0; i < 2*n; ++i){
            hilos[i].start();
        }
		
		for(int i = 0; i < 2*n; ++i){
            try {
                hilos[i].join();
            } catch (Exception ex){
                System.err.println(ex.getStackTrace());
            }
        }
        System.out.println("Fin de los hilos, elem value:" + elem.x);
		
	}
}