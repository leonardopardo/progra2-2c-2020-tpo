package tests;

import miApi.ICola;
import misImplementaciones.Cola;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.*;

public class ColaTest {

    @Test
    public void consultar_si_esta_vacia(){
        ICola cola = new Cola();
        cola.inicializarCola();
        assertTrue(cola.colaVacia() == true);
    }

    @Test
    public void ingresar_elemento_y_consultar_si_esta_vacia(){
        ICola cola = new Cola();
        cola.inicializarCola();
        cola.acolar(1);
        assertTrue(cola.colaVacia() == false);
    }

    @Test
    public void consultar_el_primero_de_la_cola(){
        ICola cola = new Cola();
        cola.inicializarCola();
        cola.acolar(1);
        assertEquals(cola.primero(), 1);
    }

    @Test
    public void desacolar_unico_elemento_y_consultar_cola_vacia(){
        ICola cola = new Cola();
        cola.inicializarCola();
        cola.acolar(1);
        cola.desacolar();
        assertTrue(cola.colaVacia() == true);
    }

    @Test
    public void consultar_por_el_ultimo_elemento_de_una_cola(){
        ICola cola = new Cola();
        cola.inicializarCola();
        cola.acolar(10);
        cola.acolar(22);
        cola.acolar(13);
        cola.acolar(4);
        cola.acolar(86);

        for (int i = 0; i < 4; i++) {
            cola.desacolar();
        }

        assertEquals(86, cola.primero());
    }

    @Test
    public void calcular_longitud_de_cola(){
        ICola cola = new Cola();
        cola.inicializarCola();
        cola.acolar(1);
        cola.acolar(2);
        cola.acolar(3);
        cola.acolar(4);
        cola.acolar(5);

        int flag = 0;

        while(!cola.colaVacia()){
            cola.desacolar();
            flag++;
        }

        assertEquals(5,flag);
    }
}
