package misImplementaciones;

import miApi.IPila;

/**
 * @Author Joaquin Merlo, Leonardo Pardo
 * @Implementación Dinámica
 * @Estrategia Primero al final.
 * @Costo
 */
public class Pila implements IPila {

    class Nodo{
        int dato;
        Nodo siguiente;
    }

    private Nodo inicio;

    @Override
    public void inicializarPila() {
        this.inicio = null;
    }

    @Override
    public void apilar(int x) {
        Nodo nuevoNodo = new Nodo();
        nuevoNodo.dato = x;
        nuevoNodo.siguiente = this.inicio;

        this.inicio = nuevoNodo;
    }

    @Override
    public void desapilar() {
        this.inicio = inicio.siguiente;
    }

    @Override
    public int tope() {
        return this.inicio.dato;
    }

    @Override
    public boolean pilaVacia() {
        return this.inicio == null;
    }
}
