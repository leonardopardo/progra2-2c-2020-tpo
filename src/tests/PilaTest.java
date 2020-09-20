package tests;

import miApi.IPila;
import misImplementaciones.Pila;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PilaTest {

    @Test
    void inicializar_pila_y_validar_si_esta_vacia(){
        IPila pila = new Pila();
        pila.inicializarPila();

        assertTrue(pila.pilaVacia());
    }

    @Test
    void apilar_elemento_y_consultar_el_tope(){
        IPila pila = new Pila();
        pila.inicializarPila();
        pila.apilar(1);

        assertEquals(pila.tope(), 1);
    }

    @Test
    void apilar_varios_elementos_y_mostrar_el_tope(){
        IPila pila = new Pila();
        pila.inicializarPila();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        assertEquals(3, pila.tope());
    }

    @Test
    void desapilar_elemento_y_mostrar_el_nuevo_tope(){
        IPila pila = new Pila();
        pila.inicializarPila();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        pila.desapilar();

        assertEquals(2, pila.tope());
    }

    @Test
    void validar_el_elemento_que_sale_de_la_pila(){
        IPila pila = new Pila();
        pila.inicializarPila();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        int tope = pila.tope();
        pila.desapilar();

        assertEquals(3, tope);
    }

    @Test
    void validar_longitud_de_pila(){
        IPila pila = new Pila();
        pila.inicializarPila();
        pila.apilar(1);
        pila.apilar(2);
        pila.apilar(3);

        int flag = 0;

        while(!pila.pilaVacia()){
            pila.desapilar();
            flag++;
        }

        assertEquals(3, flag);
    }
}
