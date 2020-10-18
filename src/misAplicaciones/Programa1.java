package misAplicaciones;

import miApi.ICola;
import miApi.IColaPrioritaria;
import miApi.IPila;
import miApi.ITabla;
import misAlgoritmos.Helpers;
import misImplementaciones.Tabla;

import java.io.IOException;

/**
 * @Author Joaquin Merlo, Leonardo Pardo.
 * 1.- Lista de los 10 últimos movimientos ordenadas de los más recientes a los menos recientes.
 * 2.- Listado de personas que solicitaron la mayor cantidad de películas, ordenadas por identificador de personas.
 */
public class Programa1 {
    public static void main(String[] args) throws IOException {

        // Lectura de Archivos y Estructuras Base
        // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
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

        // Punto 1
        // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        IPila ultimosDiezMovimientos = objHelpers.subPila(objHelpers.colaAPila(cola), 10);
        objHelpers.invertirPila(ultimosDiezMovimientos);

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

        // Punto 2
        // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        IColaPrioritaria colaP = objHelpers.metodo(cola);
        ICola colaSinOrdenar = objHelpers.subCola(colaP);
        objHelpers.ordenarCola(colaSinOrdenar);

        System.out.println("\n");

        System.out.println("Personas y cantidad de películas");
        System.out.println("=================================\n");
        System.out.println("Persona");
        while(!colaSinOrdenar.colaVacia()){
            System.out.println(colaSinOrdenar.primero());
            colaSinOrdenar.desacolar();
        }
    }
}
