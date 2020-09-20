package misImplementaciones;

import miApi.IConjunto;

public class Conjunto implements IConjunto {

    class Nodo{
        int dato;
        Nodo siguiente;
    }

    private Nodo primero;

    @Override
    public void inicializarConjunto() {
        this.primero = null;
    }

    @Override
    public void agregar(int x) {

        if(this.primero.dato == x) {
            this.primero = this.primero.siguiente;
            return;
        }

        Nodo anterior = null, actual = this.primero;

        while(actual.dato != x) {
            anterior = actual;
            actual = actual.siguiente;
        }

        anterior.siguiente = actual.siguiente;
    }

    @Override
    public void sacar(int x) {

        Nodo anterior = null, actual = this.primero;

        while(actual != null && actual.dato != x){
            anterior = actual;
            actual = actual.siguiente;
        }

        anterior.siguiente = actual.siguiente;
    }

    @Override
    public int obtener() {
        return this.primero.dato;
    }

    @Override
    public boolean conjuntoVacio() {
        return this.primero == null;
    }

    @Override
    public boolean pertenece(int x) {
        Nodo actual = this.primero;

        while(actual != null && actual.dato != x) {
            actual = actual.siguiente;
        }

        return actual != null;
    }
}
