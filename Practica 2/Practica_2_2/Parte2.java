package Practica2.Parte2;

public class Parte2 {
    public static void main(String[] args){
        int n = 10;
        DC elem = new DC();
        TieBreaker lock = new TieBreaker(n);

        Thread[] hilos = new Thread[n];

        for(int i = 0; i<n; ++i){
            hilos[i] = new Hilo(i, n, lock, elem);
        }

        System.out.println("Inicio de los hilos, elem value:"+elem.parametro);

        for(int i = 0; i<n; ++i){
            hilos[i].start();
        }


        for(int i = 0; i<n; ++i){
            try {
                hilos[i].join();
            } catch (Exception ex){
                System.err.println(ex.getStackTrace());
            }
        }
        System.out.println("Fin de los hilos, elem value:"+elem.parametro);
    }
}
