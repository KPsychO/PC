//package Parte2.Ticket;
//
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class Ticket {
//	volatile int turno[];
//	volatile int next;
//	AtomicInteger n;
//	
//	public Ticket(int n) {
//		this.n = new AtomicInteger(1);
//		turno = new int[n];
//		this.next = 1;
//	}
//	
//	public void takeLock (int id) {
//		
//		turno[id] = n.getAndAdd(1);
//		while (next != turno[id]);
//		
//	}
//	
//	public void releaseLock (int id) {
//		
//		next++;
//		
//	}
//	
//}

