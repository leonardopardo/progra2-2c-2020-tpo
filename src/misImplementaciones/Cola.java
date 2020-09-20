package misImplementaciones;

import miApi.ICola;

public class Cola implements ICola {

    class Nodo{
        int dato;
        Nodo siguiente;
    }

    private Nodo primero, ultimo;

    @Override
    public void inicializarCola() {
        this.primero = null;
        this.ultimo = null;
    }

    @Override
    public void acolar(int x) {
        Nodo n = new Nodo();
        n.dato = x;
        n.siguiente = null;

        if (this.colaVacia()) {
            this.primero = n;
            this.ultimo = n;
        } else {
            this.ultimo.siguiente = n;
            this.ultimo = n;
        }
    }

    @Override
    public void desacolar() {
        this.primero = this.primero.siguiente;
    }

    @Override
    public int primero() {
        return this.primero.dato;
    }

    @Override
    public boolean colaVacia() {
        return this.primero == null;
    }
}
