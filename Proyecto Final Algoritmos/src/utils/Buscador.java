/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import structures.ListaEnlazada;
import structures.Nodo;
import model.PruebaMolecular;

public class Buscador {

    // Busca por campo con coincidencia exacta (case-insensitive)
    public static ListaEnlazada<PruebaMolecular> buscarPorCampo(ListaEnlazada<PruebaMolecular> lista, String campo, String valor) {
        ListaEnlazada<PruebaMolecular> res = new ListaEnlazada<>();
        Nodo<PruebaMolecular> aux = lista.getCabeza();
        String v = valor == null ? "" : valor.trim().toLowerCase();

        while (aux != null) {
            PruebaMolecular p = aux.dato;
            boolean coincide = false;
            switch (campo.toLowerCase()) {
                case "uuid": coincide = p.getUuid().toLowerCase().equals(v); break;
                case "fecha": coincide = p.getFechaMuestra().toLowerCase().equals(v); break;
                case "institucion": coincide = p.getInstitucion().toLowerCase().contains(v); break;
                case "ubigeo": coincide = p.getUbigeo().toLowerCase().equals(v); break;
                case "departamento": coincide = p.getDepartamento().toLowerCase().contains(v); break;
                case "resultado": coincide = p.getResultado().toLowerCase().contains(v); break;
                // añadir más campos si deseas
                default: coincide = false;
            }

            if (coincide) res.insertarFinal(p);
            aux = aux.siguiente;
        }

        return res;
    }
}
