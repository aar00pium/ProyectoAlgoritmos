package quicklibrary.estructuras;

public class Cola<T> {

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public void enqueue(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (fin != null) {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        if (frente == null) {
            frente = nuevo;
        }
        tamanio++;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) return null;
        return frente.dato;
    }

    public boolean isEmpty() {
        return frente == null;
    }

    public int size() {
        return tamanio;
    }

    public void mostrar() {
        Nodo<T> actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println("  " + posicion + ". " + actual.dato);
            actual = actual.siguiente;
            posicion++;
        }
    }
}

