package Practica2.Parte1;

public class Hilo1 extends Thread {
    TieBreaker lock;
    DC elem;

    public Hilo1(DC elem, TieBreaker lock){
        this.elem = elem;
        this.lock = lock;
    }

    @Override
    public void run() {
        for(int i = 0; i< 100; ++i) {
            lock.in1 = true;
            lock.last = 1;
            while( lock.in2 && lock.last==1);
            System.out.println("control 1");
            lock.in1 = true;
            elem.decrementar();
            elem.incrementar();
            lock.in1 = false;
        }
    }

}
