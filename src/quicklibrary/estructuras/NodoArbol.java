package quicklibrary.estructuras;

public class NodoArbol<T> {

    T dato;                 // Dato almacenado en este nodo
    NodoArbol<T> izquierdo; // Hijo izquierdo: valores menores
    NodoArbol<T> derecho;   // Hijo derecho: valores mayores

    public NodoArbol(T dato) {
        this.dato      = dato;
        this.izquierdo = null;
        this.derecho   = null;
    }
}

