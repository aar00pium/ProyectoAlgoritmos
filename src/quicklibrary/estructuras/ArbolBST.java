package quicklibrary.estructuras;

import quicklibrary.excepciones.ElementoNoEncontradoException;

public class ArbolBST<T extends Comparable<T>> {

    private NodoArbol<T> raiz;

    public ArbolBST() {
        raiz = null;
    }

    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private NodoArbol<T> insertarRec(NodoArbol<T> nodo, T dato) {
        if (nodo == null) return new NodoArbol<>(dato);
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        else if (cmp > 0) nodo.derecho = insertarRec(nodo.derecho, dato);
        return nodo;
    }

    private T buscarRec(NodoArbol<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp == 0) return nodo.dato;
        if (cmp < 0) return buscarRec(nodo.izquierdo, dato);
        return buscarRec(nodo.derecho, dato);
    }

    public void eliminar(T dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private NodoArbol<T> insertarRec(NodoArbol<T> nodo, T dato) {
        if (nodo == null) return new NodoArbol<>(dato); // Lugar encontrado
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) nodo.izquierdo = insertarRec(nodo.izquierdo, dato); // Va a la izquierda
        else if (cmp > 0) nodo.derecho = insertarRec(nodo.derecho, dato); // Va a la derecha
        // cmp == 0: duplicado, se ignora
        return nodo;
    }

    public T buscar(T dato) {
        T resultado = buscarRec(raiz, dato);
        if (resultado == null) {
            throw new ElementoNoEncontradoException(
                "No se encontró ningún libro con ese código."
            );
        }
        return resultado;
    }

    public void inorden() {
        inordenRec(raiz);
    }

    private void inordenRec(NodoArbol<T> nodo) {
        if (nodo == null) return;
        inordenRec(nodo.izquierdo);
        System.out.println("  " + nodo.dato);
        inordenRec(nodo.derecho);
    }

    public int contar() {
        return contarRec(raiz);
    }

    private int contarRec(NodoArbol<T> nodo) {
        if (nodo == null) return 0;
        return 1 + contarRec(nodo.izquierdo) + contarRec(nodo.derecho);
    }

    public boolean isEmpty() {
        return raiz == null;
    }
}

