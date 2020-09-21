package misAlgoritmos;

import miApi.IConjunto;
import misImplementaciones.Conjunto;

public class ConjuntoAlgoritmos {

    /**
     * Copia un conjunto en otro, no se modifica el conjunto original.
     * @param conjuntoOrigen
     * @param conjuntoDestino
     */
    public void copiar(IConjunto conjuntoOrigen, IConjunto conjuntoDestino){

        IConjunto conjuntoAux = new Conjunto();
        conjuntoAux.inicializarConjunto();

        while(!conjuntoOrigen.conjuntoVacio()){
            int elem = conjuntoOrigen.obtener();
            conjuntoAux.agregar(elem);
            conjuntoOrigen.sacar(elem);
        }

        while(!conjuntoAux.conjuntoVacio()) {
            int elem = conjuntoAux.obtener();
            conjuntoOrigen.agregar(elem);
            conjuntoDestino.agregar(elem);
            conjuntoAux.sacar(elem);
        }
    }

    /**
     * Compara los valores de dos conjuntos 1 a 1, retorna verdaderos si los conjuntos son iguales. No modifica los
     * conjuntos originales.
     * @param conjuntoA
     * @param conjuntoB
     * @return
     */
    public boolean comparar(IConjunto conjuntoA, IConjunto conjuntoB){

        if(this.contarElementos(conjuntoA) != this.contarElementos(conjuntoB))
            return false;

        IConjunto conjuntoAux = new Conjunto();
        conjuntoAux.inicializarConjunto();

        int flag = 0;

        while(!conjuntoA.conjuntoVacio()){
            int elem = conjuntoA.obtener();
            conjuntoAux.agregar(elem);
            if(!conjuntoB.pertenece(elem)){
                flag++;
            }
            conjuntoA.sacar(elem);
        }

        while(!conjuntoAux.conjuntoVacio()){
            int elem = conjuntoAux.obtener();
            conjuntoA.agregar(elem);
            conjuntoAux.sacar(elem);
        }

        return flag == 0;
    }


    /**
     * Cuenta los elementos de un conjunto dado, no modifica el conjunto original.
     * @param conjunto
     * @return
     */
    public int contarElementos(IConjunto conjunto){
        IConjunto conjuntoAux = new Conjunto();
        conjuntoAux.inicializarConjunto();

        int i = 0;

        while(!conjunto.conjuntoVacio()){
            int elem = conjunto.obtener();
            conjuntoAux.agregar(elem);
            conjunto.sacar(elem);
            i++;
        }

        while(!conjuntoAux.conjuntoVacio()){
            int elem = conjuntoAux.obtener();
            conjunto.agregar(elem);
            conjuntoAux.sacar(elem);
        }

        return i;
    }
}
