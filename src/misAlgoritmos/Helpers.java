package misAlgoritmos;

import miApi.*;
import misImplementaciones.Cola;
import misImplementaciones.ColaPrioritaria;
import misImplementaciones.DiccionarioSimple;
import misImplementaciones.Pila;

import java.io.BufferedReader;
import java.io.FileReader;

public class Helpers {

    public void cargarTabla(ITabla tabla, String archivo){

        try {

            FileReader arch = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(arch);

            String linea;
            buffer.readLine();
            linea = buffer.readLine();

            while(linea != null){
                String[] lista = linea.split("\n");
                tabla.agregar(lista[0]);
                linea = buffer.readLine();
            }

            arch.close();

        } catch (Exception e) {
            System.out.println("Error de lectura. Arch : "+ archivo + ": " + e);
        }
    }

    public ICola cargarMovimientos(String archivo, ITabla tablaPeliculas, ITabla tablaProveedores){

        try {
            FileReader arch = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(arch);
            int idPersona, valor;

            String linea, nombreProveedor, nombrePelicula;
            buffer.readLine();
            linea = buffer.readLine();

            ICola cola = new Cola();
            cola.inicializarCola();

            while(linea != null){

                String[] lista = linea.split(";");
                idPersona = Integer.valueOf(lista[0]);
                nombreProveedor = lista[2].trim();
                nombrePelicula = lista[1].trim();

                int x = 0;

                if(tablaProveedores.pertenece(nombreProveedor) && tablaPeliculas.pertenece(nombrePelicula)){
                    x = (idPersona * 100  + tablaProveedores.codigo(nombreProveedor))
                            * 10000
                            + tablaPeliculas.codigo(nombrePelicula);

                    cola.acolar(x);
                }

                linea = buffer.readLine();
            }

            arch.close();

            return cola;

        } catch (Exception e) {
            System.out.println("Error de lectura. Arch : "+ archivo + ": " + e);
            return null;
        }
    }

    public IPila colaAPila(ICola cola) {

        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        IPila pila = new Pila();
        pila.inicializarPila();

        while (!cola.colaVacia()) {
            pila.apilar(cola.primero());
            colaAux.acolar(cola.primero());
            cola.desacolar();
        }

        while (!colaAux.colaVacia()) {
            cola.acolar(colaAux.primero());
            colaAux.desacolar();
        }

        return pila;
    }

    public IPila subPila(IPila pila, int cant){
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

    public void invertirPila(IPila pila){
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
    }

    public int obtenerIdPersona(int n){
        return n/1000000;
    }

    public int obtenerCodigoProveedor(int n){
        return (n/10000)%100;
    }

    public int obtenerCodigoPelicula(int n){
        return n%10000;
    }

    public IColaPrioritaria metodo(ICola colaOriginal){

        IColaPrioritaria colaP = new ColaPrioritaria();
        colaP.inicializarCola();

        IDiccionarioSimple ds = new DiccionarioSimple();
        ds.inicializarDiccionarioSimple();

        ICola cola = new Cola();
        cola.inicializarCola();
        copiarCola(colaOriginal, cola);

        while(!cola.colaVacia()){
            int idPersona = obtenerIdPersona(cola.primero());

            if(ds.claves().pertenece(idPersona)){
                int aux = ds.obtener(idPersona);
                ds.eliminar(idPersona);
                ds.agregar(idPersona, aux+1);
            } else {
                ds.agregar(idPersona, 1);
            }

            cola.desacolar();
        }

        while(!ds.claves().conjuntoVacio()){
            int clave = ds.claves().obtener();
            int aux = ds.obtener(clave);
            ds.eliminar(clave);
            colaP.acolarPrioridad(clave, aux);
        }

        return colaP;
    }

    public void copiarCola(ICola colaOrigen, ICola colaDestino) {

        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        while(!colaOrigen.colaVacia()){
            colaAux.acolar(colaOrigen.primero());
            colaDestino.acolar(colaOrigen.primero());
            colaOrigen.desacolar();
        }

        while (!colaAux.colaVacia()){
            colaOrigen.acolar(colaAux.primero());
            colaAux.desacolar();
        }
    }

    public void ordenarCola(ICola cola){
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

    public ICola subCola(IColaPrioritaria colaP){
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

    public int longitudCola(ICola cola){

        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        int size = 0;

        while (!cola.colaVacia()) {
            size++;
            colaAux.acolar(cola.primero());
            cola.desacolar();
        }

        while(!colaAux.colaVacia()) {
            cola.acolar(colaAux.primero());
            colaAux.desacolar();
        }

        return size;
    }
}