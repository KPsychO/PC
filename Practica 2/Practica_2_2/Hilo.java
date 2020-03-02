package Practica2.Parte2;


public class Hilo extends Thread {
    TieBreaker lock;
    DC elem;
    int id;
    int n;

    public Hilo(int i, int n, TieBreaker lock, DC elem){
        this.elem = elem;
        this.lock = lock;
        this.id = i;
        this.lock = lock;
        this.n=n;
    }

    @Override
    public void run() {
        int k;
        for(int i = 0; i< 50; ++i) {
            lock.lock(id);
            System.out.println("control id" + id);
            elem.decrementar();
            elem.incrementar();
            lock.in[id] = -1;
        }
    }
}
