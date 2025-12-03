/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package structures;

public class Cola<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;

    public Cola() {
        frente = fin = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public T desencolar() {
        if (estaVacia()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        return dato;
    }

    public T peek() {
        return estaVacia() ? null : frente.dato;
    }
}
