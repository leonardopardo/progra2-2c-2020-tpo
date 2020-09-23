package misImplementaciones;

import miApi.ICola;
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

        IColaCadena colaNombres = new ColaCadena();
        colaNombres.inicializarCola();

        Tupla tuplaActual = this.primera;

        while( tuplaActual != null ) {
            colaNombres.acolar( tuplaActual.nombre );
            tuplaActual = tuplaActual.siguiente;
        }

        IColaCadena colaAuxiliar = new ColaCadena();
        colaAuxiliar.inicializarCola();

        IColaCadena colaOrdenada = new ColaCadena();
        colaOrdenada.inicializarCola();

        String menor;

        while( !colaNombres.colaVacia() ) {

            menor = colaNombres.primero();

            colaNombres.desacolar();

            while( !colaNombres.colaVacia() ) {

                int x = menor.compareToIgnoreCase( colaNombres.primero() );

                if( menor.compareToIgnoreCase( colaNombres.primero() ) < 0 ) {

                    colaAuxiliar.acolar( colaNombres.primero() );

                    colaNombres.desacolar();

                } else {

                    colaAuxiliar.acolar( menor );

                    menor = colaNombres.primero();

                    colaNombres.desacolar();
                }

            }

            colaOrdenada.acolar(menor);

            while(!colaAuxiliar.colaVacia()) {

                colaNombres.acolar( colaAuxiliar.primero() );

                colaAuxiliar.desacolar();

            }

        }

        while(!colaOrdenada.colaVacia()) {

            colaNombres.acolar(colaOrdenada.primero());

            colaOrdenada.desacolar();

        }

        // TODO: EL ALGORITMO ESTÁ BIEN FALTA MODIFICAR LA TABLA, EL CODIGO A CONTINUACIÓN NO MODIFICA LA TABLA ACTUAL.
        while( !colaNombres.colaVacia() ) {

            Tupla actual = nombreElemento(colaNombres.primero());

            colaNombres.desacolar();

            if( !colaNombres.colaVacia() ) {

                actual.siguiente = nombreElemento(colaNombres.primero());

            } else {

                actual.siguiente = null;

            }
        }

    }

    // TODO: PROGRAMAR ...
    @Override
    public void ordenarCodigos(){

        ICola colaCodigos = new Cola();
        colaCodigos.inicializarCola();

        Tupla tuplaActual = this.primera;

        while( tuplaActual != null) {
            colaCodigos.acolar( tuplaActual.codigo );
        }

        ICola colaAux =new Cola();
        colaAux.inicializarCola();

        ICola colaOrdenada=new Cola();
        colaOrdenada.inicializarCola();

        int menor;

        while(!colaCodigos.colaVacia()) {

            menor = colaCodigos.primero();

            colaCodigos.desacolar();

            while(!colaCodigos.colaVacia()) {

                if(colaCodigos.primero() > menor) {

                    colaAux.acolar(colaCodigos.primero());

                    colaCodigos.desacolar();

                } else {

                    colaAux.acolar(menor);

                    menor = colaCodigos.primero();

                    colaCodigos.desacolar();
                }

            }

            colaOrdenada.acolar(menor);

            while(!colaAux.colaVacia()) {

                colaCodigos.acolar(colaAux.primero());

                colaAux.desacolar();

            }

        }
        while(!colaOrdenada.colaVacia()) {

            colaCodigos.acolar(colaOrdenada.primero());

            colaOrdenada.desacolar();

        }

        while(!colaCodigos.colaVacia()) {

            Tupla actual = codigoElemento(colaCodigos.primero());

            colaCodigos.desacolar();

            if( !colaCodigos.colaVacia() ) {
                actual.siguiente = codigoElemento(colaCodigos.primero());
            } else {
                actual.siguiente = null;
            }
        }
    }

    @Override
    public boolean estaVacia() {
        return this.primera == null;
    }

    /**
     *
     * @param codigo
     * @return
     */
    private Tupla codigoElemento(int codigo) {

        Tupla tuplaActual = this.primera;

        while ( tuplaActual != null && tuplaActual.codigo != codigo ) {
            tuplaActual = tuplaActual.siguiente;
        }

        return(tuplaActual);
    }

    /**
     *
     * @param nombre
     * @return
     */
    private Tupla nombreElemento(String nombre) {

        Tupla tuplaActual = this.primera;

        while(tuplaActual != null && tuplaActual.nombre != nombre) {
            tuplaActual = tuplaActual.siguiente;
        }

        return(tuplaActual);
    }
}
