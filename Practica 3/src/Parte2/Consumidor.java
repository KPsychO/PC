package Parte2;

import java.util.concurrent.Semaphore;

public class Consumidor extends Thread{
    int idProceso;
    int n;
    Semaphore empty;
    Semaphore full;
    Almacen almacen;

    public Consumidor(int idProceso, int n, Semaphore empty, Semaphore full, Almacen almacen) {
        this.idProceso = idProceso;
        this.n = n;
        this.empty = empty;
        this.full = full;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        Producto producto;

        for (int i = 0; i<n; ++i){

            try {
                this.full.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            producto = almacen.extraer();

            this.empty.release();

            System.out.println("Consumidor"+this.idProceso+": " + producto);

        }

    }
}
