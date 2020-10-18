package misAlgoritmos;

import miApi.*;
import misImplementaciones.Cola;
import misImplementaciones.ColaPrioritaria;
import misImplementaciones.DiccionarioSimple;
import misImplementaciones.Pila;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @Author Joaquin Merlo, Leonardo Pardo.
 */
public class Helpers {

    /**
     * Recorre un archivo de texto, obtiene el valor de cada renglón y lo agrega a una estructura Tabla.
     * @param tabla
     * @param archivo
     */
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

    /**
     * Recorre un archivo de texto separado por punto y coma, se sapara cada valor, se compara con las tablas de película
     * y la de proveedores y se obtiene una estructura de Cola con la estructura IdPersona-codigoProveedor-codigoPelicula
     * XXYYYZZZZ.
     * @param archivo
     * @param tablaPeliculas
     * @param tablaProveedores
     * @return ICola
     */
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

    /**
     * Pasa de una estructura de Cola a una de Pila y retorna la Pila.
     * La Cola Original no se borra.
     * @param cola
     * @return IPila
     */
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

    /**
     * Genera una Pila de n elementos, la Pila Original y la cantidad de elementos son argumentos.
     * La Pila original no se borra ni pierde elementos.
     * @param pila
     * @param cant
     * @return IPila
     */
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

    /**
     * Invierte una Pila dada.
     * @param pila
     */
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

    /**
     * Dado el valor generado para la Cola de Movimientos extrae el id de Persona.
     * @param n
     * @return
     */
    public int obtenerIdPersona(int n){
        return n/1000000;
    }

    /**
     * Dado el valor generado para la Cola de Movimientos extrae el código del Proveedor.
     * @param n
     * @return
     */
    public int obtenerCodigoProveedor(int n){
        return (n/10000)%100;
    }

    /**
     * Dado el valor generado para la Cola de Movimientos extrae el código de Pelicula.
     * @param n
     * @return
     */
    public int obtenerCodigoPelicula(int n){
        return n%10000;
    }

    /**
     * Recibe la cola de movimientos y retorna una cola con prioridad donde el elemento es el id de Persona y la
     * prioridad representa la cantidad de Péliculas.
     * @param colaOriginal
     * @return
     */
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

    /**
     * Copia una Cola de Origen a otra Cola de Destino, ambas colas vienen por argumento.
     * @param colaOrigen
     * @param colaDestino
     */
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

    /**
     * Ordena los valores de una Cola de forma ascendente.
     * Recibe la cola por argumento y la modifica.
     * @param cola
     */
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

    /**
     * Recibe una cola con prioridad y retorna una cola con todos los elementos de mayor prioridad.
     * @param colaP
     * @return
     */
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

    /**
     * Calcula la cantidad de elementos de una cola.
     * La Cola no se modifica.
     * @param cola
     * @return
     */
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