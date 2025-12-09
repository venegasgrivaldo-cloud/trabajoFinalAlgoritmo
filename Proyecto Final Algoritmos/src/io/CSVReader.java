package io;

import java.io.BufferedReader;
import java.io.FileReader;
import model.PruebaMolecular;
import structures.ListaEnlazada;

public class CSVReader {

    public static ListaEnlazada<PruebaMolecular> cargarCSV(String ruta) {
        ListaEnlazada<PruebaMolecular> lista = new ListaEnlazada<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;
            br.readLine(); // saltar cabecera

            while ((linea = br.readLine()) != null) {
                String[] data = linea.split(",");

                PruebaMolecular p = new PruebaMolecular(
                        data[0], // uuid
                        data[1], // fecha muestra
                        data[2], // institución
                        data[3], // ubigeo
                        Integer.parseInt(data[4]), // edad
                        data[5], // sexo
                        data[6], // dpto
                        data[7], // prov
                        data[8], // dist
                        data[9], // tipo muestra
                        data[10] // resultado
                );

                lista.insertarFinal(p);
            }

        } catch (Exception e) {
            System.out.println("Error cargando CSV: " + e.getMessage());
        }

        return lista;
    }
}
