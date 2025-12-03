/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import structures.ListaEnlazada;
import model.PruebaMolecular;

public class DataStore {
    private static DataStore instancia;
    private ListaEnlazada<PruebaMolecular> pruebas;

    private DataStore() {
        pruebas = new ListaEnlazada<>();
    }

    public static DataStore getInstance() {
        if (instancia == null) instancia = new DataStore();
        return instancia;
    }

    public ListaEnlazada<PruebaMolecular> getPruebas() {
        return pruebas;
    }

    public void setPruebas(ListaEnlazada<PruebaMolecular> p) {
        pruebas = p;
    }
}

