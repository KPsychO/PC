package Practica3;

public class Decrementor extends Thread{
    int id;
    Mutex mutex;
    DC elem;
    public Decrementor(int id, Mutex mutex, DC elem){
        this.id = id;
        this.mutex = mutex;
        this.elem = elem;
    }

    @Override
    public void run(){
        for(int i = 0; i< 1000; ++i) {
            mutex.lock(id);
            elem.decrementar();
            mutex.unlock(id);
        }
    }
}
