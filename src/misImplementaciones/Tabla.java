package misImplementaciones;

import miApi.IColaCadena;
import miApi.ITabla;

public class Tabla implements ITabla {
    @Override
    public void inicializarTabla() {

    }

    @Override
    public void agregar(int codigo) {

    }

    @Override
    public boolean pertenece(String nombre) {
        return false;
    }

    @Override
    public int codigo(String nombre) {
        return 0;
    }

    @Override
    public IColaCadena tabla() {
        return null;
    }

    @Override
    public void ordenarNombres() {

    }

    @Override
    public void ordenarCodigos() {

    }

    @Override
    public boolean estaVacia() {
        return false;
    }
}
