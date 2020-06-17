package Parte2;



import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){
        Almacen almacen = new AlmacenImpl();

        Semaphore empty = new Semaphore(1);
        Semaphore full = new Semaphore(0);
        int procesos = 2;
        int iteraciones = 5;

        //public Productor(int idProceso, int n, Semaphore empty, Semaphore full, Producto buf){
        Productor[] productores = new Productor[procesos];
        Consumidor[] consumidores = new Consumidor[procesos];

        System.out.println("Creando hilos");
        for(int i = 0; i<procesos; ++i){
            productores[i] = new Productor(i+1, iteraciones, empty, full, almacen);
            consumidores[i] = new Consumidor(i+1, iteraciones, empty, full, almacen);
        }

        System.out.println("Hilos creados, inicializandolos...");

        for(int i = 0; i<procesos; ++i){
            productores[i].start();
            consumidores[i].start();
        }

        System.out.println("Hilos inicializados, sincronizandose...");

        for(int i = 0; i<procesos; ++i){
            try {
                productores[i].join();
                consumidores[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Hilos han filizado");
    }

}
