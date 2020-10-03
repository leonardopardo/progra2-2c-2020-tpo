package misAplicaciones;

import miApi.ICola;
import miApi.ITabla;
import misAlgoritmos.Helpers;
import misImplementaciones.Tabla;

import java.io.IOException;

/**
 * 1.- Lista de los 10 últimos movimientos ordenadas de los más recientes a los menos recientes.
 * 2.- Listado de personas que solicitaron la mayor cantidad de películas, ordenadas por identificador de personas.
 */
public class Programa1 {

    public static void main(String[] args) throws IOException {

        ITabla tablaProveedores = new Tabla();
        tablaProveedores.inicializarTabla();

        ITabla tablaPeliculas = new Tabla();
        tablaPeliculas.inicializarTabla();

        Helpers objHelpers = new Helpers();
        objHelpers.cargarTabla(tablaProveedores, "~/../files/StreamCia.txt");
        objHelpers.cargarTabla(tablaPeliculas, "~/../files/LISTADO DE PELICULAS.txt");

        ICola cola = objHelpers.cargarMovimientos(
                "~/../files/Movimientos.txt",
                tablaPeliculas,
                tablaProveedores
        );



    }

    public static void mostrarCola(ICola cola) {
        while (!cola.colaVacia()) {
            System.out.print(cola.primero() + "\n");
            cola.desacolar();
        }
        System.out.println();
    }
}
