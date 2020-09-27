package misAlgoritmos;

import miApi.ITabla;

import java.io.BufferedReader;
import java.io.FileReader;

public class Helpers {

    public void cargarMovimientos(String archivo) {
        try {
            FileReader arch = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(arch);
            int dni, valor;

            String linea, nombreProveedor, nombrePelicula;
            buffer.readLine();
            linea = buffer.readLine();

            while(linea != null){
                String[] lista = linea.split(";");
                dni = Integer.valueOf(lista[0]);
                nombreProveedor = lista[2];
                nombrePelicula = lista[1];
                linea = buffer.readLine();
            }

            arch.close();

        } catch (Exception e) {
            System.out.println("Error de lectura. Arch : "+ archivo + ": " + e);
        }
    }

    public void cargarTabla(ITabla tabla, String archivo){

        try {

            FileReader arch = new FileReader(archivo);
            BufferedReader buffer = new BufferedReader(arch);

            String linea;
            buffer.readLine();
            linea = buffer.readLine();

            while(linea != null){
                String[] lista = linea.split("\n");
                tabla.agregar(lista[0]);
                linea = buffer.readLine();
            }

            arch.close();

        } catch (Exception e) {
            System.out.println("Error de lectura. Arch : "+ archivo + ": " + e);
        }
    }

}
