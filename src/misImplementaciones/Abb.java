package misImplementaciones;

import miApi.IAbb;

public class Abb implements IAbb {

    class Nodo {
        int valor;
        IAbb der, izq;
    }

    private Nodo root;

    @Override
    public void inicializar() {
        this.root = null;
    }

    @Override
    public int raiz() {
        return this.root.valor;
    }

    @Override
    public IAbb hijoIzq() {
        return null;
    }

    @Override
    public IAbb hijoDer() {
        return null;
    }

    @Override
    public void agregar(int x) {
        if(this.root == null){
            this.root = new Nodo();
            this.root.valor = x;
            this.root.izq = new Abb();
            this.root.izq.inicializar();
            this.root.der = new Abb();
            this.root.der.inicializar();
        } else if(x < this.root.valor){
            this.hijoIzq().agregar(x);
        } else {
            this.hijoDer().agregar(x);
        }
    }

    @Override
    public void eliminar(int x) {

    }

    @Override
    public boolean arbolVacio() {
        return false;
    }

    @Override
    public boolean pertenece() {
        return false;
    }
}
