package Practica3.Parte1;

import Practica3.DC;
import Practica3.Decrementor;
import Practica3.Incrementor;
import Practica3.Mutex;

public class Parte1 {

    public static void main(String[] args){
        int n = 100;
        Thread threads[] = new Thread[n];
        DC elemento = new DC();
        Mutex mutex = new Ticket(n);

        System.out.println("Inicializando hilos, elem value:"+elemento.resource);
        for(int i = 0; i<n/2; ++i){
            threads[i] = new Incrementor(i, mutex, elemento);
            threads[i+50] = new Decrementor(i+50, mutex, elemento);
        }

        for (int i = 0; i<n; ++i){
            threads[i].start();
        }

        for(int i = 0; i<n; ++i){
            try {
                threads[i].join();
            } catch (Exception ex){
                System.err.println(ex.getStackTrace());
            }
        }
        System.out.println("Fin de los hilos, elem value:"+elemento.resource);
    }
}
