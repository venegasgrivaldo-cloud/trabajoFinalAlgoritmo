/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import structures.ListaEnlazada;
import structures.Nodo;
import model.PruebaMolecular;

public class Ordenador {

    public static ListaEnlazada<PruebaMolecular> mergeSortByEdad(ListaEnlazada<PruebaMolecular> lista) {
        Nodo<PruebaMolecular> head = lista.getCabeza();
        Nodo<PruebaMolecular> sorted = mergeSortRec(head);
        ListaEnlazada<PruebaMolecular> resultado = new ListaEnlazada<>();
        Nodo<PruebaMolecular> aux = sorted;
        while (aux != null) {
            resultado.insertarFinal(aux.dato);
            aux = aux.siguiente;
        }
        return resultado;
    }

    private static Nodo<PruebaMolecular> mergeSortRec(Nodo<PruebaMolecular> head) {
        if (head == null || head.siguiente == null) return head;
        Nodo<PruebaMolecular> mid = getMiddle(head);
        Nodo<PruebaMolecular> right = mid.siguiente;
        mid.siguiente = null;

        Nodo<PruebaMolecular> leftSorted = mergeSortRec(head);
        Nodo<PruebaMolecular> rightSorted = mergeSortRec(right);

        return merge(leftSorted, rightSorted);
    }

    private static Nodo<PruebaMolecular> merge(Nodo<PruebaMolecular> a, Nodo<PruebaMolecular> b) {
        Nodo<PruebaMolecular> dummy = new Nodo<>(null);
        Nodo<PruebaMolecular> tail = dummy;

        while (a != null && b != null) {
            if (a.dato.getEdad() <= b.dato.getEdad()) {
                tail.siguiente = a;
                a = a.siguiente;
            } else {
                tail.siguiente = b;
                b = b.siguiente;
            }
            tail = tail.siguiente;
        }

        tail.siguiente = (a != null) ? a : b;
        return dummy.siguiente;
    }

    private static Nodo<PruebaMolecular> getMiddle(Nodo<PruebaMolecular> head) {
        if (head == null) return head;
        Nodo<PruebaMolecular> slow = head, fast = head.siguiente;
        while (fast != null && fast.siguiente != null) {
            slow = slow.siguiente;
            fast = fast.siguiente.siguiente;
        }
        return slow;
    }
}

