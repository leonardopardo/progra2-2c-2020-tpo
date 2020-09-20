package tests;

import miApi.IConjunto;
import misImplementaciones.Conjunto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ConjuntoTest {
    @Test
    void inicialiar_conjunto_y_validar_si_esta_vacio(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();

        assertTrue(conjunto.conjuntoVacio());
    }

    @Test
    void agregar_elemento_y_validar_vacio(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(1);

        assertFalse(conjunto.conjuntoVacio());
    }

    @Test
    void agregar_elmentos_y_validar_pertenencia(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(10);
        conjunto.agregar(12);
        conjunto.agregar(18);
        conjunto.agregar(27);
        conjunto.agregar(-2);

        assertTrue(conjunto.pertenece(18));
    }

    @Test
    void obtener_elemento_y_quitarlo_del_conjunto(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(18);
        conjunto.agregar(27);
        conjunto.agregar(-2);
        conjunto.agregar(10);
        conjunto.agregar(12);

        int val = conjunto.obtener();
        conjunto.sacar(val);

        assertFalse(conjunto.pertenece(val));
    }

    @Test
    void validar_longitud_del_conjuto(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(18);
        conjunto.agregar(27);
        conjunto.agregar(-2);
        conjunto.agregar(10);
        conjunto.agregar(12);

        int flag = 0;

        while(!conjunto.conjuntoVacio()){
            int val = conjunto.obtener();
            conjunto.sacar(val);
            flag++;
        }

        assertEquals(5, flag);
    }

    @Test
    void sacar_elemento_y_recalcular_longitud_del_conjunto(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(18);
        conjunto.agregar(27);
        conjunto.agregar(-2);
        conjunto.agregar(10);
        conjunto.agregar(12);

        int val = conjunto.obtener();
        conjunto.sacar(val);

        int flag = 0;

        while(!conjunto.conjuntoVacio()){
            conjunto.sacar(conjunto.obtener());
            flag++;
        }

        assertEquals(4, flag);
    }

    @Test
    void validar_elemento_duplicado_antes_de_insertarlo(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(10);
        conjunto.agregar(16);
        conjunto.agregar(101);

        if(!conjunto.pertenece(10))
            conjunto.agregar(10);

        int flag = 0;

        while(!conjunto.conjuntoVacio()){
            int val = conjunto.obtener();
            conjunto.sacar(val);
            flag++;
        }

        assertEquals(3, flag);
    }
}
