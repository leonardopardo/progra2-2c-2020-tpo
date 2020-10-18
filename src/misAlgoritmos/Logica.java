package misAlgoritmos;

import miApi.ICola;
import miApi.IColaPrioritaria;
import miApi.IDiccionarioSimple;
import miApi.IPila;
import misImplementaciones.Cola;
import misImplementaciones.ColaPrioritaria;
import misImplementaciones.DiccionarioSimple;
import misImplementaciones.Pila;

public class Logica {

    private IPila pila;

    public Logica(){
        this.pila = new Pila();
        this.pila.inicializarPila();
    }

    public void cargarElementos(int elem){
        this.pila.apilar(elem);
    }

    private IPila copiarPila(IPila pila){
        IPila pilaAux = new Pila();
        pilaAux.inicializarPila();

        while(!pila.pilaVacia()) {
            pilaAux.apilar(pila.tope());
            pila.desapilar();
        }

        IPila pilaDestino = new Pila();
        pilaDestino.inicializarPila();

        while(!pilaAux.pilaVacia()) {
            pilaDestino.apilar(pilaAux.tope());
            pila.apilar(pilaAux.tope());
            pilaAux.desapilar();
        }

        return pilaDestino;
    }

    private ICola copiarCola(ICola cola){
        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        ICola colaDestino = new Cola();
        colaDestino.inicializarCola();

        while(!cola.colaVacia()){
            colaAux.acolar(cola.primero());
            colaDestino.acolar(cola.primero());
            cola.desacolar();
        }

        while (!colaAux.colaVacia()){
            cola.acolar(colaAux.primero());
            colaAux.desacolar();
        }

        return colaDestino;
    }

    private IPila pilaInvertida(IPila pila) {
        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        while(!pila.pilaVacia()){
            colaAux.acolar(pila.tope());
            pila.desapilar();
        }

        while(!colaAux.colaVacia()) {
            pila.apilar(colaAux.primero());
            colaAux.desacolar();
        }

        return pila;
    }

    private IPila subPila(IPila pila, int cant){
        IPila pilaAux = new Pila();
        pilaAux.inicializarPila();

        IPila pilaSalida = new Pila();
        pilaSalida.inicializarPila();

        for (int i = 0; i < cant; i++) {
            pilaAux.apilar(pila.tope());
            pilaSalida.apilar(pila.tope());
            pila.desapilar();
        }

        for (int i = 0; i < cant; i++) {
            pila.apilar(pilaAux.tope());
            pilaAux.desapilar();
        }

        return pilaSalida;
    }

    private int idPersona(int n){
        return n/1000000;
    }

    private int codigoProveedor(int n){
        return (n/10000)%100;
    }

    private int codigoPelicula(int n){
        return n%10000;
    }

    private void ordenarCola(ICola cola){
        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        ICola colaOrd = new Cola();
        colaOrd.inicializarCola();

        while(!cola.colaVacia()){
            int menor = cola.primero();
            cola.desacolar();

            while(!cola.colaVacia()){

                if(menor > cola.primero()){
                    int aux = menor;
                    menor = cola.primero();
                    colaAux.acolar(aux);
                } else {
                    colaAux.acolar(cola.primero());
                }

                cola.desacolar();
            }

            while(!colaAux.colaVacia()){
                cola.acolar(colaAux.primero());
                colaAux.desacolar();
            }

            colaOrd.acolar(menor);
        }

        while(!colaOrd.colaVacia()){
            cola.acolar(colaOrd.primero());
            colaOrd.desacolar();
        }
    }

    private ICola altaPrioridad(IColaPrioritaria colaP){
        int max = colaP.prioridad();

        IColaPrioritaria colaPAux = new ColaPrioritaria();
        colaPAux.inicializarCola();

        ICola colaSalida = new Cola();
        colaSalida.inicializarCola();

        while(!colaP.colaVacia() && colaP.prioridad() == max){
            colaPAux.acolarPrioridad(colaP.primero(), colaP.prioridad());
            colaSalida.acolar(colaP.primero());
            colaP.desacolar();
        }

        while(!colaPAux.colaVacia()){
            colaP.acolarPrioridad(colaPAux.primero(), colaPAux.prioridad());
            colaPAux.desacolar();
        }

        return colaSalida;
    }

    public IPila ultimosDiezMovimientos(){
        IPila pila = this.copiarPila(this.pila);
        return pilaInvertida(subPila(pila, 10));
    }

    public ICola mejoresClientes(){

        IColaPrioritaria colaP = new ColaPrioritaria();
        colaP.inicializarCola();

        IDiccionarioSimple ds = new DiccionarioSimple();
        ds.inicializarDiccionarioSimple();

        IPila pila = copiarPila(this.pila);

        while(!pila.pilaVacia()){
            int valor = 1;
            int idPersona = this.idPersona(pila.tope());

            if(ds.claves().pertenece(idPersona)){
                int aux = ds.obtener(idPersona);
                ds.eliminar(idPersona);
                ds.agregar(idPersona, aux+1);
            } else {
                ds.agregar(idPersona, 1);
            }

            pila.desapilar();
        }

        while(!ds.claves().conjuntoVacio()){
            int clave = ds.claves().obtener();
            int aux = ds.obtener(clave);
            ds.eliminar(clave);
            colaP.acolarPrioridad(clave, aux);
        }

        ICola colaSalida = this.altaPrioridad(colaP);
        this.ordenarCola(colaSalida);
        return colaSalida;
    }

}
