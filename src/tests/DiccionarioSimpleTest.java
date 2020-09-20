package tests;

import miApi.IConjunto;
import miApi.IDiccionarioSimple;
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
    void cambiar_el_dato_de_una_clave_y_devolver_su_valor(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.agregar(1, 16);
        d.agregar(1, 122);

        assertEquals(122, d.obtener(1));
    }

    @Test
    void eliminar_elmento_del_conjunto_y_validar_conjunto_vacio(){
        IDiccionarioSimple d = new DiccionarioSimple();
        d.inicializarDiccionarioSimple();

        d.agregar(1, 10);
        d.eliminar(1);

        assertTrue(d.claves().conjuntoVacio());
    }
}
