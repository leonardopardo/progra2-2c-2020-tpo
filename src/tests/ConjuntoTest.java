package tests;

import miApi.IConjunto;
import misAlgoritmos.ConjuntoAlgoritmos;
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

    @Test
    void contar_elmentos_de_un_conjunto(){
        IConjunto conjunto = new Conjunto();
        conjunto.inicializarConjunto();
        conjunto.agregar(18);
        conjunto.agregar(27);
        conjunto.agregar(-2);
        conjunto.agregar(10);
        conjunto.agregar(12);

        ConjuntoAlgoritmos cAlg = new ConjuntoAlgoritmos();

        assertEquals(5, cAlg.contarElementos(conjunto));
    }

    @Test
    void comparar_dos_conjuntos(){
        IConjunto cA = new Conjunto();
        cA.inicializarConjunto();
        cA.agregar(1);
        cA.agregar(2);
        cA.agregar(3);
        cA.agregar(4);

        IConjunto cB = new Conjunto();
        cB.inicializarConjunto();
        cB.agregar(1);
        cB.agregar(2);
        cB.agregar(3);
        cB.agregar(4);

        ConjuntoAlgoritmos cAlg = new ConjuntoAlgoritmos();

        assertTrue(cAlg.comparar(cA, cB));
    }
}
