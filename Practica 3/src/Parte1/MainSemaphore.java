package Parte1;

import java.util.concurrent.Semaphore;

public class MainSemaphore {

    public static void main(String[] args){
        int n = 10;
        int iteraciones = 100;
        Recurso recurso = new Recurso();

        Semaphore semaforo = new Semaphore(1);

        HiloIncrementor[] incrementors = new HiloIncrementor[n];
        HiloDecrementor[] decrementors = new HiloDecrementor[n];

        System.out.println("Creando hilos...");

        for(int i = 0; i<n; i++){
            incrementors[i] = new HiloIncrementor(i, recurso, semaforo, iteraciones);
            decrementors[i] = new HiloDecrementor(i+n, recurso, semaforo, iteraciones);
        }

        System.out.println("Hilos creados.");
        System.out.println("Inicializando hilos...");

        for(int i = 0; i<n; i++){
            incrementors[i].start();
            decrementors[i].start();
        }

        System.out.println("Hilos creados.");
        System.out.println("Esperando a la finalizacion de los hilos...");

        for(int i = 0; i<n; i++){
            try {
                incrementors[i].join();
                decrementors[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Hilos finalizados.");
        System.out.println("Valor del recurso: "+recurso.i);

    }

}
