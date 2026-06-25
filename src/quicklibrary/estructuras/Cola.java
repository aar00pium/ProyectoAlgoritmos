package quicklibrary.estructuras;

import quicklibrary.excepciones.ColaVaciaException;

public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente  = null;
        fin     = null;
        tamanio = 0;
    }

    public void enqueue(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin != null) fin.siguiente = nuevo;
        fin = nuevo;
        if (frente == null) frente = nuevo;
        tamanio++;
    }

    public T dequeue() {
        if (isEmpty()) throw new ColaVaciaException("No hay solicitudes pendientes.");
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) fin = null;
        tamanio--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) throw new ColaVaciaException("No hay solicitudes pendientes.");
        return frente.dato;
    }

    public boolean isEmpty() { return frente == null; }

    public int size() { return tamanio; }

    public void mostrar() {
        if (isEmpty()) { System.out.println("  [La cola esta vacia]"); return; }
        Nodo<T> actual = frente;
        int pos = 1;
        while (actual != null) {
            System.out.println("  " + pos + ". " + actual.dato);
            actual = actual.siguiente;
            pos++;
        }
    }
}

