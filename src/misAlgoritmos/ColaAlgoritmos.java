package misAlgoritmos;

import miApi.ICola;
import miApi.IPila;
import misImplementaciones.Pila;

public class ColaAlgoritmos {
    /**
     * Invierte una cola de enteros dada.
     * @param cola
     */
    public void invertirCola(ICola cola){
        IPila pila = new Pila();
        pila.inicializarPila();

        while(!cola.colaVacia()){
            pila.apilar(cola.primero());
            cola.desacolar();
        }

        while(!pila.pilaVacia()){
            cola.acolar(pila.tope());
            pila.desapilar();
        }
    }
}
