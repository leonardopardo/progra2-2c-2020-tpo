package misImplementaciones;

import miApi.IConjunto;
import miApi.IGrafo;

/**
 * @Author Joaquin Merlo, Leonardo Pardo
 * @Implementación Dinámica
 * @Estrategia Ordena por prioririodiad, la primera es la más alta.
 * @Costo
 */
public class Grafo implements IGrafo {
    @Override
    public void inicializarGrafo() {

    }

    @Override
    public void agregarVertice(int vertice) {

    }

    @Override
    public void eliminarVertice(int vertice) {

    }

    @Override
    public void agregarArista(int verticeOrigen, int verticeDestino, int peso) {

    }

    @Override
    public void eliminarArista(int verticeOrigen, int verticeDestino) {

    }

    @Override
    public IConjunto vertices() {
        return null;
    }

    @Override
    public int peso(int verticeOrigen, int verticeDestino) {
        return 0;
    }

    @Override
    public boolean existeArista(int verticeOrigen, int verticeDestino) {
        return false;
    }
}
