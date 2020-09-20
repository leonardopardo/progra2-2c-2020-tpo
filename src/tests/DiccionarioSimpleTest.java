package tests;

import miApi.IConjunto;
import miApi.IDiccionarioSimple;
import misAlgoritmos.ConjuntoAlgoritmos;
import misImplementaciones.Conjunto;
import misImplementaciones.DiccionarioSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiccionarioSimpleTest {
    @Test
    void inicializar_diccionario(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        assertTrue(d.claves().conjuntoVacio());
    }

    @Test
    void agregar_elementos_al_diccionario_y_obtener_alguno_por_clave(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.agregar(2, 11);
        d.agregar(3, 12);
        d.agregar(4, 13);
        d.agregar(5, 14);
        d.agregar(6, 15);

        assertEquals(12, d.obtener(3));
    }

    @Test
    void agregar_elementos_al_diccionario_y_contar_la_cantidad_de_claves(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.agregar(2, 11);
        d.agregar(3, 12);
        d.agregar(4, 13);
        d.agregar(5, 14);
        d.agregar(6, 15);

        ConjuntoAlgoritmos cAlg = new ConjuntoAlgoritmos();

        assertEquals(6, cAlg.contarElementos(d.claves()));
    }

    @Test
    void cambiar_el_dato_de_una_clave_y_devolver_su_valor(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.agregar(1, 16);
        d.agregar(1, 122);

        assertEquals(122, d.obtener(1));
    }

    @Test
    void eliminar_unico_elmento_del_diccionario_y_validar_si_esta_vacio(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.eliminar(1);

        assertTrue(d.claves().conjuntoVacio());
    }

    @Test
    void eliminar_un_elemento_del_diccionario_y_contar_la_cantidad_de_claves(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.agregar(2, 11);
        d.agregar(3, 12);
        d.agregar(4, 13);
        d.agregar(5, 14);
        d.agregar(6, 15);

        d.eliminar(3);

        ConjuntoAlgoritmos cAlg = new ConjuntoAlgoritmos();

        assertEquals(5, cAlg.contarElementos(d.claves()));
    }

    @Test
    void eliminar_un_elemento_del_diccionario_y_validar_las_claves(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.agregar(2, 11);
        d.agregar(3, 12);
        d.agregar(4, 13);
        d.agregar(5, 14);
        d.agregar(6, 15);
        d.eliminar(3);

        IConjunto c = new Conjunto();
        c.inicializarConjunto();
        c.agregar(1);
        c.agregar(2);
        c.agregar(4);
        c.agregar(5);
        c.agregar(6);

        ConjuntoAlgoritmos cAlg = new ConjuntoAlgoritmos();
        assertTrue(cAlg.comparar(d.claves(), c));
    }
}
