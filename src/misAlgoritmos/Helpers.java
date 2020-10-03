package misAlgoritmos;

import miApi.ICola;
import miApi.IPila;
import miApi.ITabla;
import misImplementaciones.Cola;

import java.io.BufferedReader;
import java.io.FileReader;

public class Helpers {

    public ICola cargarMovimientos(String archivo, ITabla tablaPeliculas, ITabla tablaProveedores) {

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

    public void colaEnPila(ICola cola, IPila pila) {

        ICola colaAux = new Cola();
        colaAux.inicializarCola();

        while (!cola.colaVacia()) {
            pila.apilar(cola.primero());
            colaAux.acolar(cola.primero());
            cola.desacolar();
        }

        while (!colaAux.colaVacia()) {
            cola.acolar(colaAux.primero());
            colaAux.desacolar();
        }
    }

}
