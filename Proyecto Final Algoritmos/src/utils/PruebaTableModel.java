/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.table.AbstractTableModel;
import structures.ListaEnlazada;
import structures.Nodo;
import model.PruebaMolecular;

public class PruebaTableModel extends AbstractTableModel {

    private String[] columnas = {"UUID", "Fecha", "Institución", "Ubigeo", "Edad", "Sexo", "Dpto", "Prov", "Dist", "Tipo Muestra", "Resultado"};
    private ListaEnlazada<PruebaMolecular> datos;

    public PruebaTableModel(ListaEnlazada<PruebaMolecular> lista) {
        this.datos = lista;
    }

    @Override
    public int getRowCount() {
        return datos == null ? 0 : datos.tamaño();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // recorrer hasta rowIndex
        Nodo<PruebaMolecular> aux = datos.getCabeza();
        int idx = 0;
        while (aux != null && idx < rowIndex) {
            aux = aux.siguiente;
            idx++;
        }
        if (aux == null) return null;
        PruebaMolecular p = aux.dato;
        switch (columnIndex) {
            case 0: return p.getUuid();
            case 1: return p.getFechaMuestra();
            case 2: return p.getInstitucion();
            case 3: return p.getUbigeo();
            case 4: return p.getEdad();
            case 5: return p.getSexo();
            case 6: return p.getDepartamento();
            case 7: return p.getProvincia();
            case 8: return p.getDistrito();
            case 9: return p.getTipoMuestra();
            case 10: return p.getResultado();
            default: return null;
        }
    }
}

