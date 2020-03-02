package Practica2.Parte1;

public class Parte1 {

    public static void main(String[] args){

        DC elem = new DC();
        TieBreaker lock = new TieBreaker();
        Thread hil1 = new Hilo1(elem, lock);
        Thread hil2 = new Hilo2(elem, lock);

        System.out.println("Inicio de los hilos, elem value:"+elem.parametro);

        hil1.start();
        hil2.start();

        try {
            hil1.join();
            hil2.join();
        } catch (Exception ex){
            System.err.println(ex.getStackTrace());
        }

        System.out.println("Fin de los hilos, elem value:"+elem.parametro);
    }
}
