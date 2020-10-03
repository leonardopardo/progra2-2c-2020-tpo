import miApi.IAbb;
import miApi.IColaCadena;
import miApi.ITabla;
import misAlgoritmos.Helpers;
import misImplementaciones.Tabla;

import java.io.IOException;

public class Main {

    static class Nodo{
        int valor;
        Nodo siguiente;
    }

    public static void main(String[] args) throws IOException {

        int[] vec = {1,2,3,4,5};
        System.out.println("\nSUMA DE VECTOR");
        System.out.println("============================");
        System.out.println(sumarVectorRecursivo(vec, vec.length));


        System.out.println("\nPRINT LISTA");
        System.out.println("============================");
        Nodo lista = null;
        Nodo nuevo;

        for (int i = 0; i < 5; i++) {
            nuevo = new Nodo();
            nuevo.valor = (int)(Math.random()*40+i);
            nuevo.siguiente = lista;
            lista = nuevo;
        }

        Nodo a = lista;

        while( a != null){
            System.out.println(a.valor);
            a = a.siguiente;
        }

        System.out.println("\nEL MENOR DE LA LISTA");
        System.out.println("============================");
        System.out.println(menorListaRecursivo(lista));


        System.out.println("\nTABLA");
        System.out.println("============================");
        ITabla t = new Tabla();
        t.inicializarTabla();
        t.agregar("Natalia");
        t.agregar("Nicolás");
        t.agregar("Federico");
        t.agregar("Leonardo");

        t.ordenarNombres();
        t.ordenarCodigos();

        IColaCadena colaTabla = t.tabla();

        while(!colaTabla.colaVacia()){
            System.out.println(colaTabla.primero());
            colaTabla.desacolar();
        }

        System.out.println("\nLEER ARCHIVO");
        System.out.println("============================");
        Helpers objHelpers = new Helpers();

        ITabla tablaCia = new Tabla();
        tablaCia.inicializarTabla();

        ITabla tablaPeli = new Tabla();
        tablaPeli.inicializarTabla();

        objHelpers.cargarTabla(tablaCia, "~/../files/StreamCia.txt");
        objHelpers.cargarTabla(tablaPeli, "~/../files/LISTADO DE PELICULAS.txt");

        IColaCadena colaCia = tablaCia.tabla();

        while(!colaCia.colaVacia()){
            System.out.println(colaCia.primero());
            colaCia.desacolar();
        }

        IColaCadena colaPeli = tablaPeli.tabla();

        while(!colaPeli.colaVacia()){
            System.out.println(colaPeli.primero());
            colaPeli.desacolar();
        }
    }

    public static int sumarVectorRecursivo(int[] v, int tam){
        return tam == 1
            ? v[tam-1]
            : sumarVectorRecursivo(v, tam-1) + v[tam-1];
    }

    public static int menorListaRecursivo(Nodo primero) {
        if (primero.siguiente == null)
            return primero.valor;

        int min = menorListaRecursivo(primero.siguiente);

        return primero.valor < min
                ? primero.valor
                : min;
    }

    public static void orden(IAbb arbol){
        if(!arbol.arbolVacio()){
            orden(arbol.hijoIzq());
            System.out.println(arbol.raiz());
            orden(arbol.hijoDer());
        }
    }

    public static void preorden(IAbb arbol){
        if(!arbol.arbolVacio()){
            System.out.println(arbol.raiz());
            orden(arbol.hijoIzq());
            orden(arbol.hijoDer());
        }
    }

    public static void posorden(IAbb arbol){
        if(!arbol.arbolVacio()){
            orden(arbol.hijoIzq());
            orden(arbol.hijoDer());
            System.out.println(arbol.raiz());
        }
    }

}