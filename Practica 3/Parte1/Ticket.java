package Practica3.Parte1;

import Practica3.Mutex;
import java.util.concurrent.atomic.AtomicInteger;

public class Ticket implements Mutex {
    AtomicInteger number;
    volatile int next;
    int turns[];

    public Ticket(int n){
        number = new AtomicInteger(1);
        next = 1;
        turns = new int[n];
    }

    @Override
    public void lock(int id) {
        turns[id] = number.getAndAdd(1);
        while (next!=turns[id]);
    }

    @Override
    public void unlock(int id) {
        next++;
        //System.out.println(next);
    }
}
