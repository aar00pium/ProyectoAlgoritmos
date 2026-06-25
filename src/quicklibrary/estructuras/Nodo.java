package quicklibrary.estructuras;

public class Nodo<T> {

    T dato;            // Valor almacenado en este nodo
    Nodo<T> siguiente; // Referencia al siguiente nodo (null si es el ultimo)

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

