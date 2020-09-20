package tests;

import miApi.ILista;
import misImplementaciones.Lista;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListaTest {
    @Test
    void inicializar_lista(){
        ILista l = new Lista();
        l.inicializarLista();

        assertTrue(l.listaVacia());
    }

    @Test
    void agregar_elementos_y_validar_existencia_de_algunos(){
        ILista l = new Lista();
        l.inicializarLista();
        l.agregarInicio(77);
        l.agregarInicio(124);
        l.agregarInicio(14);
        l.agregarInicio(36);
        l.agregarInicio(-7);

        assertTrue(l.existe(36) && l.existe(14));
    }

    @Test
    void agregar_elementos_y_validar_no_existencia_uno(){
        ILista l = new Lista();
        l.inicializarLista();
        l.agregarInicio(77);
        l.agregarInicio(124);
        l.agregarInicio(14);
        l.agregarInicio(36);
        l.agregarInicio(-7);

        assertFalse(l.existe(1));
    }

    @Test
    void agregar_al_inicio_eliminar_al_inicio_y_validar_existencia(){
        ILista l = new Lista();
        l.inicializarLista();

        l.agregarInicio(77);
        l.agregarInicio(124);
        l.agregarInicio(14);
        l.agregarInicio(36);
        l.agregarInicio(-7);
        l.eliminarInicio();

        assertFalse(l.existe(-7));
    }

    @Test
    void agregar_al_inicio_eliminar_al_final_y_validar_existencia(){
        ILista l = new Lista();
        l.inicializarLista();

        l.agregarInicio(77);
        l.agregarInicio(124);
        l.agregarInicio(14);
        l.agregarInicio(36);
        l.agregarInicio(-7);
        l.eliminarFinal();

        assertFalse(l.existe(77));
    }

    @Test
    void agregar_al_final_eliminar_al_inicio_y_validar_existencia(){
        ILista l = new Lista();
        l.inicializarLista();
        l.agregarFinal(77);
        l.agregarFinal(124);
        l.agregarFinal(14);
        l.agregarFinal(36);
        l.agregarFinal(-7);
        l.eliminarInicio();

        assertFalse(l.existe(77));
    }

    @Test
    void agregar_al_final_eliminar_al_final_y_validar_existencia(){
        ILista l = new Lista();
        l.inicializarLista();

        l.agregarFinal(77);
        l.agregarFinal(124);
        l.agregarFinal(14);
        l.agregarFinal(36);
        l.agregarFinal(-7);
        l.eliminarFinal();

        assertFalse(l.existe(-7));
    }
}
