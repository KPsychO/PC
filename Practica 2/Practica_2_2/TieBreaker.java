package Practica2.Parte2;

public class TieBreaker {
    volatile int in[];
    volatile int last[];
    volatile int n;

    public TieBreaker(int n){
        in = new int[n];
        last = new int[n];
        this.n = n;
        for(int i = 0; i<n; ++i){
            in[i] = -1;
            last[i] = -1;
        }
    }

    public void lock(int id){
        for(int j = 0; j < n; j++){
            in[id] = j;
            in = in;
            last[j] = id;
            last = last;
            for(int k = 0; k<n; ++k)
                if(k!=id)
                    while(in[k]>=in[id] && last[j]==id);
        }

    }

}
