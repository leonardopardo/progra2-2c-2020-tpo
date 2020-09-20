package miApi;

/**
 * Árbol de búsqueda binaria es una colección de elementos ordenados en forma jerárquica (todos números enteros). Entre
 * dos elementos vinculados se define la relación padre-hijo. Cada elemento tiene sólo un padre, excepto el elemento
 * raíz del árbol que no tiene padre.
 */
public interface IArbolBinario {
    /**
     * @Tarea permite inicializar el árbol.
     * @Precondición
     */
    public void inicializarABB();

    /**
     * @Tarea devuelve el valor de la raíz. No elimina el elemento.
     * @Precondición El árbol no puede estar vacío.
     */
    public int raiz();

    /**
     * @Tarea devuelve la referencia al subárbol izquierdo. No elimina los elementos del sub-árbol.
     * @Precondición El árbol no puede estar vacío.
     */
    public int hijoIzq();

    /**
     * @Tarea devuelve la referencia al subárbol derecho. No elimina los elementos del sub-árbol.
     * @Precondición El árbol no puede estar vacío.
     */
    public int hijoDer();

    /**
     * @Tarea agrega un valor suministrado.
     * @Precondición El árbol debe estar inicializado y no debe existir el valor.
     */
    public void agregar(int x);

    /**
     * @Tarea elimina un valor suministrado.
     * @Precondición El valor debe existir.
     */
    public void eliminar(int x);

    /**
     * @Tarea devuelve verdadero si el árbol no contiene elementos.
     * @Precondición La estructura debe estar inicializada.
     */
    public boolean arbolVacio();

    /**
     * @Tarea devuelve verdadero si el valor suministrado pertenece al árbol.
     * @Precondición EL árbol debe estar inicializado.
     */
    public boolean pertence(int x);
}