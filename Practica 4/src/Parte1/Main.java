//package Parte1;
//
//public class Main {
//	
//	public static void main(String[] args) throws InterruptedException {
//		int P = 10; // Producers
//		int C = 10; // Consumers
//		int buff_len = 20; // Buffer length
//		
//		Monitor monitor = new Monitor(buff_len, buff_len);
//		Thread[] producers = new Thread[P];
//		Thread[] consumers = new Thread[C];
//		
//		for (int i = 0; i < P; i++) {
//			producers[i] = new Producer(monitor, i);
//			producers[i].start();
//			
//		}
//		
//		for (int i = 0; i < C; i++) {
//			consumers[i] = new Consumer(monitor, i);
//			consumers[i].start();
//			
//		}
//		
//		for (int i = 0; i < P; i++) {
//			producers[i].join();
//		}
//		
//		for (int i = 0; i < C; i++) {
//			consumers[i].join();
//		}
//		
//	}
//
//}