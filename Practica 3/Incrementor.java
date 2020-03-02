package Practica3;

public class Incrementor extends Thread {
    int id;
    Mutex mutex;
    DC elem;

    public Incrementor(int id, Mutex mutex, DC elem) {
        this.id = id;
        this.mutex = mutex;
        this.elem = elem;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; ++i) {
            mutex.lock(id);
            elem.incrementar();
            mutex.unlock(id);
        }
    }
}