package misAplicaciones;

import miApi.*;
import misAlgoritmos.Helpers;
import misImplementaciones.*;

import java.io.IOException;

/**
 * 3.- Lista de películas que fueron más solicitadas, junto a que empresas fueron solicitadas cada una.
 */
public class Programa2 {
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

        // Generar diccionarios con la información necesaria para los puntos 3, 4 y 5

        // Diccionario donde la clave es la pelicula y el valor es la cantidad de veces
        IDiccionarioSimple dicPeliCantidad = new DiccionarioSimple();
        dicPeliCantidad.inicializarDiccionarioSimple();

        // Diccionario donde la clave es la pelicula y los valores son los proveedores
        IDiccionarioMultiple dicPeliProveedor = new DiccionarioMultiple();
        dicPeliProveedor.inicializarDiccionarioMultiple();

        // Generar diccionario donde las claves son las personas y los valores las películas que pidieron
        IDiccionarioMultiple dicPersonaPeli=new DiccionarioMultiple();
        dicPersonaPeli.inicializarDiccionarioMultiple();

        // Generar diccionario donde las claves son "personas+pelicula" y los valores los proveedores usados
        IDiccionarioMultiple dicPerPeliProveedor=new DiccionarioMultiple();
        dicPerPeliProveedor.inicializarDiccionarioMultiple();

        // Llenarlos
        while(!cola.colaVacia()) {

            int codigo=cola.primero();
            int id_persona=objHelpers.obtenerIdPersona(codigo);
            int id_pelicula=objHelpers.obtenerCodigoPelicula(codigo);
            int id_proveedor=objHelpers.obtenerCodigoProveedor(codigo);
            int id_mix=id_persona*10000+id_pelicula;

            if (!dicPeliCantidad.claves().pertenece(id_pelicula)) {
                dicPeliCantidad.agregar(id_pelicula,1);
            } else {
                int cantidad=dicPeliCantidad.obtener(id_pelicula);
                cantidad++;
                dicPeliCantidad.eliminar(id_pelicula);
                dicPeliCantidad.agregar(id_pelicula,cantidad);
            }

            if(!dicPeliProveedor.claves().pertenece(id_pelicula)) {
                dicPeliProveedor.agregar(id_pelicula,id_proveedor);
            } else {
                if (!dicPeliProveedor.obtener(id_pelicula).pertenece(id_proveedor)) {
                    dicPeliProveedor.agregar(id_pelicula,id_proveedor);
                }
            }

            if(!dicPersonaPeli.claves().pertenece(id_persona)) {
                dicPersonaPeli.agregar(id_persona,id_pelicula);
            } else {
                if (!dicPersonaPeli.obtener(id_persona).pertenece(id_pelicula)) {
                    dicPersonaPeli.agregar(id_persona,id_pelicula);
                }
            }

            if(!dicPerPeliProveedor.claves().pertenece(id_mix)) {
                dicPerPeliProveedor.agregar(id_mix,id_proveedor);
            } else {
                if (!dicPerPeliProveedor.obtener(id_mix).pertenece(id_proveedor)) {
                    dicPerPeliProveedor.agregar(id_mix,id_proveedor);
                }
            }

            cola.desacolar();
        }

        // Punto 3
        // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

        /*Para obtener listado de peliculas ordenados de mayor a menor cantidad pasar a una
         * cola prioridad el diccionario simple (peli-cantidad)
         */
        IConjunto claves_peliculas = new Conjunto();
        claves_peliculas.inicializarConjunto();
        claves_peliculas = dicPeliCantidad.claves();

        IColaPrioritaria colaClavesCantidad = new ColaPrioritaria();
        colaClavesCantidad.inicializarCola();

        IColaPrioritaria colaClavesCantidad_aux = new ColaPrioritaria();
        colaClavesCantidad_aux.inicializarCola();

        while(!claves_peliculas.conjuntoVacio()) {
            int pelicula = claves_peliculas.obtener();
            colaClavesCantidad.acolarPrioridad(pelicula, dicPeliCantidad.obtener(pelicula));
            colaClavesCantidad_aux.acolarPrioridad(pelicula, dicPeliCantidad.obtener(pelicula));
            claves_peliculas.sacar(pelicula);
        }

        System.out.println("3. Películas más solicitadas con proveedor");
        System.out.println("==========================================\n");
        System.out.println("Película\tProveedores");

        while(!colaClavesCantidad.colaVacia()) {
            String peli_string = Integer.toString(colaClavesCantidad.primero());

            IConjunto proveedores = dicPeliProveedor.obtener(colaClavesCantidad.primero());
            String proveedores_string = "";

            while(!proveedores.conjuntoVacio()) {
                int proveedor=proveedores.obtener();
                proveedores_string = proveedores_string + Integer.toString(proveedor);
                proveedores.sacar(proveedor);

                if(!proveedores.conjuntoVacio()) {
                    proveedores_string = proveedores_string + ",";
                }
            }

            System.out.println(peli_string + "\t\t\t [" + proveedores_string + "]");

            colaClavesCantidad.desacolar();
        }

        // Punto 4
        // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        System.out.println("\n");
        System.out.println("4. Películas más solicitadas con proveedor");
        System.out.println("==========================================\n");
        System.out.println("Película\tCantidad");

        int i = 0;

        while( i < 10 ) {

            String pelicula_str = Integer.toString(colaClavesCantidad_aux.primero());

            String cantidad = Integer.toString(colaClavesCantidad_aux.prioridad());

            System.out.println(pelicula_str + "\t\t\t\t" + cantidad);

            colaClavesCantidad_aux.desacolar();

            i++;
        }

        // Punto 5
        // :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        IConjunto conjuntoPerPeli = new Conjunto();
        conjuntoPerPeli=dicPersonaPeli.claves();

        IConjunto valores = new Conjunto();
        valores.inicializarConjunto();

        IColaCadena cola_resultados = new ColaCadena();
        cola_resultados.inicializarCola();

        while(!conjuntoPerPeli.conjuntoVacio()) {

            int persona = conjuntoPerPeli.obtener();
            valores = dicPersonaPeli.obtener(persona);

            while(!valores.conjuntoVacio()) {

                int pelicula=valores.obtener();

                int peliPer = persona * 10000 + pelicula;

                IConjunto proves = new Conjunto();
                proves=dicPerPeliProveedor.obtener(peliPer);

                String proves_str = "";
                int cant_prov = 0;

                while(!proves.conjuntoVacio()) {

                    int proveedor_codigo=proves.obtener();

                    proves_str = proves_str + proveedor_codigo;

                    proves.sacar(proveedor_codigo);

                    if(!proves.conjuntoVacio()) {
                        proves_str = proves_str + ",";
                    }

                    cant_prov++;
                }

                if( cant_prov > 1 ) {

                    int persona_final = peliPer / 1000;

                    int pelicula_final = peliPer % 1000;

                    cola_resultados.acolar(persona_final + "\t\t" + pelicula_final + "(" + proves_str + ")");
                }

                valores.sacar(pelicula);
            }

            conjuntoPerPeli.sacar(persona);
        }

        System.out.println("\n");
        System.out.println("5. Personas que solicitaron películas iguales a distintas companías");
        System.out.println("==========================================\n");
        System.out.println("Persona\t\tPelícula\tProveedores");

        while(!cola_resultados.colaVacia()) {
            System.out.println(cola_resultados.primero());
            cola_resultados.desacolar();
        }
    }
}
