package Parte2;

public class AlmacenImpl implements Almacen{
    Producto buff;

    public AlmacenImpl(){

    }

    @Override
    public void almacenar(Producto producto) {
        this.buff = producto;
    }

    @Override
    public Producto extraer() {
        Producto dummy = this.buff;
        this.buff = null;
        return dummy;
    }
}
