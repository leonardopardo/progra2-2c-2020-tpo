package misImplementaciones;

import miApi.IColaCadena;
import miApi.ITabla;

public class Tabla implements ITabla {

    class Tupla{
        int codigo;
        String nombre;
        Tupla siguiente;
    }

    private Tupla primera;
    private int tuplas;

    @Override
    public void inicializarTabla() {
        this.primera = null;
        this.tuplas = 0;
    }

    @Override
    public void agregar(String nombre) {
        Tupla nuevaTupla = new Tupla();
        nuevaTupla.codigo = this.tuplas;
        nuevaTupla.nombre = nombre;
        nuevaTupla.siguiente = null;

        Tupla tuplaActual = this.primera;

        if (tuplaActual == null){
            this.primera = nuevaTupla;
            this.tuplas++;
            return;
        }

        while(tuplaActual.siguiente != null){
            tuplaActual = tuplaActual.siguiente;
        }

        tuplaActual.siguiente = nuevaTupla;

        this.tuplas++;
    }

    @Override
    public boolean pertenece(String nombre) {
        Tupla tuplaActual = this.primera;

        while(tuplaActual != null && tuplaActual.nombre != nombre){
            tuplaActual = tuplaActual.siguiente;
        }

        return tuplaActual != null;
    }

    @Override
    public int codigo(String nombre) {
        Tupla tuplaActual = this.primera;

        while (tuplaActual != null && tuplaActual.nombre != nombre) {
            tuplaActual = tuplaActual.siguiente;
        }

        return tuplaActual.codigo;
    }

    @Override
    public IColaCadena tabla() {
        Tupla tuplaActual = this.primera;
        IColaCadena cola = new ColaCadena();
        cola.inicializarCola();

        while (tuplaActual != null){

            cola.acolar( Integer
                    .toString(tuplaActual.codigo)
                    .concat(";")
                    .concat(tuplaActual.nombre)
            );

            tuplaActual = tuplaActual.siguiente;
        }

        return cola;
    }

    // TODO: PROGRAMAR ...
    @Override
    public void ordenarNombres() {
    }

    // TODO: PROGRAMAR ...
    @Override
    public void ordenarCodigos(){

    }

    @Override
    public boolean estaVacia() {
        return this.primera == null;
    }
}
