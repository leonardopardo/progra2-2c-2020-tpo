package misAplicaciones;

import miApi.ICola;
import miApi.IPila;
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

        IPila ultimosDiezMovimientos = objHelpers.subPila(objHelpers.colaAPila(cola), 10);

        System.out.println("ULTIMAS 10 SOLICITUDES");
        System.out.println("=================================\n");
        System.out.println("Persona\tProveedor\tPelicula");

        while(!ultimosDiezMovimientos.pilaVacia()){
            String salida =  objHelpers.obtenerIdPersona(ultimosDiezMovimientos.tope()) + "\t\t\t";
            salida += objHelpers.obtenerCodigoProveedor(ultimosDiezMovimientos.tope()) + "\t\t\t";
            salida += objHelpers.obtenerCodigoPelicula(ultimosDiezMovimientos.tope());

            System.out.println(salida);
            ultimosDiezMovimientos.desapilar();
        }
    }
}
