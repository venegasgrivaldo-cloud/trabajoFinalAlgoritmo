/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures;

import model.PruebaMolecular;

public class ArbolBST {
    private class NodoBST {
        PruebaMolecular dato;
        NodoBST izq, der;
        NodoBST(PruebaMolecular p) { dato = p; izq = der = null; }
    }

    private NodoBST raiz;

    public ArbolBST() {
        raiz = null;
    }

    // Inserta por UUID (clave única)
    public void insertar(PruebaMolecular p) {
        raiz = insertarRec(raiz, p);
    }

    private NodoBST insertarRec(NodoBST nodo, PruebaMolecular p) {
        if (nodo == null) return new NodoBST(p);
        if (p.getUuid().compareTo(nodo.dato.getUuid()) < 0) {
            nodo.izq = insertarRec(nodo.izq, p);
        } else {
            nodo.der = insertarRec(nodo.der, p);
        }
        return nodo;
    }

    // recorrido inorder: devuelve ListaEnlazada con orden por UUID
    public structures.ListaEnlazada<PruebaMolecular> inorder() {
        structures.ListaEnlazada<PruebaMolecular> res = new structures.ListaEnlazada<>();
        inorderRec(raiz, res);
        return res;
    }

    private void inorderRec(NodoBST nodo, structures.ListaEnlazada<PruebaMolecular> lista) {
        if (nodo == null) return;
        inorderRec(nodo.izq, lista);
        lista.insertarFinal(nodo.dato);
        inorderRec(nodo.der, lista);
    }
}

