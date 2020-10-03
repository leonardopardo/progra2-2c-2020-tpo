package tests;

import miApi.IAbb;
import misImplementaciones.Abb;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AbbTest {
    @Test
    public void inicializar_arbol(){
        IAbb a = new Abb();
        a.inicializar();

        assertTrue(a.arbolVacio());
    }
    @Test
    public void agregar_valores_y_consultar_la_raiz(){
        IAbb a = new Abb();
        a.inicializar();
        a.agregar(10);
        a.agregar(20);
        a.agregar(15);
        a.agregar(30);

        assertEquals(10, a.raiz());
    }

    @Test
    public void agregar_valores_eliminar_raiz_y_consultar_la_raiz(){
        IAbb a = new Abb();
        a.inicializar();
        a.agregar(10);
        a.agregar(20);
        a.agregar(15);
        a.agregar(30);

        a.eliminar(10);
        assertEquals(15, a.raiz());
    }

    @Test
    public void agregar_valores_eliminar_raiz_y_consultar_el_hijo_derecho(){
        IAbb a = new Abb();
        a.inicializar();
        a.agregar(10);
        a.agregar(20);
        a.agregar(15);
        a.agregar(30);

        a.eliminar(10);
        assertEquals(20, a.hijoDer().raiz());
    }
}
