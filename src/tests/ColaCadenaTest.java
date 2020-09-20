package tests;

import miApi.IColaCadena;
import misImplementaciones.ColaCadena;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColaCadenaTest{
    @Test
    public void consultar_si_esta_vacia(){
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();
        assertTrue(cola.colaVacia());
    }

    @Test
    public void ingresar_elemento_y_consultar_si_esta_vacia(){
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();
        cola.acolar("Hola Mundo");
        assertFalse(cola.colaVacia());
    }

    @Test
    public void consultar_el_primero_de_la_cola(){
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();
        cola.acolar("Primero");
        assertEquals(cola.primero(), "Primero");
    }

    @Test
    public void desacolar_unico_elemento_y_consultar_cola_vacia(){
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();
        cola.acolar("Hola Mundo");
        cola.desacolar();
        assertTrue(cola.colaVacia());
    }

    @Test
    public void consultar_por_el_ultimo_elemento_de_una_cola(){
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();
        cola.acolar("Primero");
        cola.acolar("Segundo");
        cola.acolar("Tercero");
        cola.acolar("Cuarto");
        cola.acolar("Quinto");

        for (int i = 0; i < 4; i++) {
            cola.desacolar();
        }

        assertEquals("Quinto", cola.primero());
    }

    @Test
    public void calcular_longitud_de_cola(){
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();
        cola.acolar("Primero");
        cola.acolar("Segundo");
        cola.acolar("Tercero");
        cola.acolar("Cuarto");
        cola.acolar("Quinto");

        int flag = 0;

        while(!cola.colaVacia()){
            cola.desacolar();
            flag++;
        }

        assertEquals(5,flag);
    }
}
