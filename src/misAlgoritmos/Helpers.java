package misAlgoritmos;

import miApi.ICola;
import miApi.IPila;
import miApi.ITabla;
import misImplementaciones.Cola;
import misImplementaciones.Pila;

import java.io.BufferedReader;
import java.io.FileReader;

public class Helpers {

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

    public int obtenerIdPersona(int n){
        return n/1000000;
    }

    public int obtenerCodigoProveedor(int n){
        return (n/10000)%100;
    }

    public int obtenerCodigoPelicula(int n){
        return n%10000;
    }
}
