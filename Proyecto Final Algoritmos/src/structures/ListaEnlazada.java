
package structures;

public class ListaEnlazada<T> {

    private Nodo<T> cabeza;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo<T> aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
    }

    public Nodo<T> getCabeza() {
        return cabeza;
    }

    public int tamaño() {
        int cont = 0;
        Nodo<T> aux = cabeza;
        while (aux != null) {
            cont++;
            aux = aux.siguiente;
        }
        return cont;
    }
}
 

