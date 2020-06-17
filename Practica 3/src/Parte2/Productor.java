package Parte2;

import java.util.concurrent.Semaphore;

public class Productor extends Thread{
    int idProceso;
    int n;
    Semaphore empty;
    Semaphore full;
    Almacen almacen;

    public Productor(int idProceso, int n, Semaphore empty, Semaphore full, Almacen almacen){
        this.idProceso = idProceso;
        this.n = n;
        this.empty = empty;
        this.full = full;
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 0; i<n; ++i){
            try {
                this.empty.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            almacen.almacenar(new Producto(idProceso, i));
            System.out.println("Productor"+this.idProceso+":" + new Producto(idProceso, i) );
            this.full.release();

        }
    }
}
