package Practica3.Parte2;

import Practica3.DC;
import Practica3.Decrementor;
import Practica3.Incrementor;
import Practica3.Mutex;

public class Parte2 {

    public static void main(String[] args){
        int n = 80;
        Thread threads[] = new Thread[n];
        DC elemento = new DC();
        Mutex mutex = new Backery(n);

        System.out.println("Inicializando hilos, elem value:"+elemento.resource);
        for(int i = 0; i<n/2; ++i){
            threads[i] = new Incrementor(i, mutex, elemento);
            threads[i+(n/2)] = new Decrementor(i+(n/2), mutex, elemento);
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
