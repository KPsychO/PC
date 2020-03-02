package Practica2.Parte1;

public class TieBreaker {
    volatile boolean in1;
    volatile boolean in2;
    volatile int last;

    public TieBreaker(){
        in1 = false;
        in2 = false;
    }

}
