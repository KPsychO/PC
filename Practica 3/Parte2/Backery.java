package Practica3.Parte2;

import Practica3.Mutex;

public class Backery implements Mutex {
    volatile int turns[];
    int n;

    public Backery(int n){
        this.n = n;
        turns = new int[n];

    }

    @Override
    public void lock(int id) {
        int max = 0;
        for(int i = 0; i<n; ++i){
            if (turns[i]>max){
                max = turns[i];
            }
        }
        turns[id] = max+1;

        for (int i = 0; i<n; ++i){
            if(i!=id){
                while (turns[i]!=0 && ( turns[id] > turns[i] ||(turns[i]==turns[id] && id > i) ));
            }
        }

    }

    @Override
    public void unlock(int id) {
        turns[id] = 0;
        turns = turns;
        //System.out.println("fin ejecucion");
    }
}
