package tests;

import miApi.IDiccionarioMultiple;
import misImplementaciones.DiccionarioMultiple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiccionarioMultipleTest {
    @Test
    void inicializar_diccionario_multiple(){
        IDiccionarioMultiple d = new DiccionarioMultiple();
        d.inicializarDiccionarioMultiple();

        assertTrue(d.claves().conjuntoVacio());
    }

    @Test
    void agregar_elementos_a_diccionario(){
        IDiccionarioMultiple d = new DiccionarioMultiple();
        d.inicializarDiccionarioMultiple();
        d.agregar(1, 10);
        d.agregar(1, 11);
        d.agregar(1, 12);
        d.agregar(2, 14);
        d.agregar(2, 22);

        assertFalse(d.claves().conjuntoVacio());
    }

    @Test
    void agregar_elementos_a_diccionario_y_validar_pertenencia(){
        IDiccionarioMultiple d = new DiccionarioMultiple();
        d.inicializarDiccionarioMultiple();
        d.agregar(1, 10);
        d.agregar(1, 11);
        d.agregar(1, 12);
        d.agregar(2, 14);
        d.agregar(2, 22);

        assertTrue(d.obtener(2).pertenece(14));
    }

    @Test
    void eliminar_elemento_unico_y_validar_vacio(){
        IDiccionarioMultiple d = new DiccionarioMultiple();
        d.inicializarDiccionarioMultiple();
        d.agregar(1, 10);
        d.eliminarValor(1, 10);

        assertTrue(d.claves().conjuntoVacio());
    }

    @Test
    void eliminar_valor_y_validar_pertenencia_del_elemento(){
        IDiccionarioMultiple d = new DiccionarioMultiple();
        d.inicializarDiccionarioMultiple();
        d.agregar(1, 10);
        d.agregar(1, 11);
        d.agregar(1, 12);
        d.agregar(2, 14);
        d.agregar(2, 22);
        d.eliminarValor(2, 22);

        assertFalse(d.obtener(2).pertenece(22));
    }
}
