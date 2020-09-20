package tests;

import miApi.IConjunto;
import misImplementaciones.Conjunto;
import org.junit.jupiter.api.Test;
import static junit.framework.TestCase.*;

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

        assertTrue(conjunto.pertenece(1));
    }
}
