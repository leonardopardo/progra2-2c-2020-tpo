package tests;

import miApi.IColaPrioritaria;
import misImplementaciones.ColaPrioritaria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColaPrioritariaTest {
    @Test
    void inicializar_cola_prioritaria_y_valiar(){
        IColaPrioritaria cola = new ColaPrioritaria();
        cola.inicializarCola();

        assertTrue(cola.colaVacia());
    }

    @Test
    void acolar_elemento_y_obtener_el_valor(){
        IColaPrioritaria cola = new ColaPrioritaria();
        cola.inicializarCola();

        cola.acolarPrioridad(121, 7);
        cola.acolarPrioridad(14, 4);
        cola.acolarPrioridad(1, 2);
        cola.acolarPrioridad(166, 3);
        cola.acolarPrioridad(28, 4);

        assertEquals(121, cola.primero());
    }

    @Test
    void acolar_elemento_y_obtener_la_prioridad(){
        IColaPrioritaria cola = new ColaPrioritaria();
        cola.inicializarCola();

        cola.acolarPrioridad(121, 7);
        cola.acolarPrioridad(14, 4);
        cola.acolarPrioridad(1, 2);
        cola.acolarPrioridad(166, 3);
        cola.acolarPrioridad(28, 4);

        assertEquals(7, cola.prioridad());
    }

    @Test
    void acolar_dos_elementos_de_igual_prioridad_y_obtener_el_primero(){
        IColaPrioritaria cola = new ColaPrioritaria();
        cola.inicializarCola();

        cola.acolarPrioridad(121, 7);
        cola.acolarPrioridad(14, 7);

        assertEquals(121, cola.primero());
    }

    @Test
    void acolar_dos_elementos_con_el_mismo_dato_y_diferente_prioridad(){
        IColaPrioritaria cola = new ColaPrioritaria();
        cola.inicializarCola();

        cola.acolarPrioridad(14, 4);
        cola.acolarPrioridad(14, 3);

        assertEquals(4, cola.prioridad());
    }

    @Test
    void desacolar_elemento_de_mayor_prioridad_y_validar_la_siguiente(){
        IColaPrioritaria cola = new ColaPrioritaria();
        cola.inicializarCola();

        cola.acolarPrioridad(14, 4);
        cola.acolarPrioridad(14, 3);

        cola.desacolar();

        assertEquals(3, cola.prioridad());
    }

}
