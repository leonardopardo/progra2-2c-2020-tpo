package misImplementaciones;

import miApi.IConjunto;
import miApi.IDiccionarioMultiple;

public class DiccionarioMultiple implements IDiccionarioMultiple {

    class Nodo {
        int clave;
        Conjunto valor;
        Nodo siguiente;
    }

    @Override
    public void inicializarDiccionarioMultiple() {

    }

    @Override
    public void agregar(int clave, int valor) {

    }

    @Override
    public void eliminar(int clave) {

    }

    @Override
    public void eliminarValor(int clave) {

    }

    @Override
    public IConjunto obtener(int clave) {
        return null;
    }

    @Override
    public IConjunto claves() {
        return null;
    }
}
