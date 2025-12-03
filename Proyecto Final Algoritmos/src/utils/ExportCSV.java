/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import structures.ListaEnlazada;
import structures.Nodo;
import model.PruebaMolecular;

public class ExportCSV {

    public static boolean exportar(ListaEnlazada<PruebaMolecular> lista, String ruta) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ruta))) {
            // cabecera (ajusta según tu CSV original)
            pw.println("uuid;fechaMuestra;institucion;ubigeo;edad;sexo;departamento;provincia;distrito;tipoMuestra;resultado");
            Nodo<PruebaMolecular> aux = lista.getCabeza();
            while (aux != null) {
                PruebaMolecular p = aux.dato;
                String linea = String.join(";",
                    p.getUuid(),
                    p.getFechaMuestra(),
                    p.getInstitucion(),
                    p.getUbigeo(),
                    String.valueOf(p.getEdad()),
                    p.getSexo(),
                    p.getDepartamento(),
                    p.getProvincia(),
                    p.getDistrito(),
                    p.getTipoMuestra(),
                    p.getResultado()
                );
                pw.println(linea);
                aux = aux.siguiente;
            }
            return true;
        } catch (Exception e) {
            System.out.println("Error exportando CSV: " + e.getMessage());
            return false;
        }
    }
}

