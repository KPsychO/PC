package Parte2;

public class Producto {
    int proceso;
    int iteracion;

    public Producto(int proceso, int iteracion) {
        this.proceso = proceso;
        this.iteracion = iteracion;
    }

    @Override
    public String toString() {
        return "proceso: " + this.proceso + ", iteracion:" + this.iteracion;
    }
}
