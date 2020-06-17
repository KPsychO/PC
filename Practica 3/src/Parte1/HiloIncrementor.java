package Parte1;

import java.util.concurrent.Semaphore;

public class HiloIncrementor extends Thread {
    Semaphore semaforo;
    Recurso recurso;
    int id, iteraciones;

    public HiloIncrementor(int id, Recurso recurso, Semaphore semaforo, int iteraciones){
        this.semaforo = semaforo;
        this.recurso = recurso;
        this.id = id;
        this.iteraciones = iteraciones;
    }

    @Override
    public void run() {
        for(int i = 0; i<iteraciones; i++){
            try {
                semaforo.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recurso.incr();
            semaforo.release();
        }
    }

}
