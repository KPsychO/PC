package Practica2.Parte1;

public class Hilo2 extends Thread {
    TieBreaker lock;
    DC elem;

    public Hilo2(DC elem, TieBreaker lock){
        this.elem = elem;
        this.lock = lock;
    }

    @Override
    public void run() {
        for(int i = 0; i< 100; ++i) {

            lock.in2 = true;
            lock.last = 2;
            while( lock.in1 && lock.last==2);
            System.out.println("control 2");
            lock.in2 = true;
            elem.decrementar();
            elem.incrementar();
            lock.in2 = false;
        }
    }

}
