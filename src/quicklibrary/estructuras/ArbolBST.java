package quicklibrary.estructuras;

import quicklibrary.excepciones.ElementoNoEncontradoException;

public class ArbolBST<T extends Comparable<T>> {

    private NodoArbol<T> raiz;

    public ArbolBST() { raiz = null; }

    // ── Insertar ──────────────────────────────────────────────────────────────

    public void insertar(T dato) { raiz = insertarRec(raiz, dato); }

    private NodoArbol<T> insertarRec(NodoArbol<T> nodo, T dato) {
        if (nodo == null) return new NodoArbol<>(dato);
        int cmp = dato.compareTo(nodo.dato);
        if      (cmp < 0) nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        else if (cmp > 0) nodo.derecho   = insertarRec(nodo.derecho,   dato);
        return nodo;
    }

    // ── Buscar ────────────────────────────────────────────────────────────────

    public T buscar(T dato) {
        T r = buscarRec(raiz, dato);
        if (r == null) throw new ElementoNoEncontradoException(
            "No se encontro ningun libro con ese codigo.");
        return r;
    }

    private T buscarRec(NodoArbol<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp == 0) return nodo.dato;
        return cmp < 0 ? buscarRec(nodo.izquierdo, dato) : buscarRec(nodo.derecho, dato);
    }

    public boolean contiene(T dato) { return buscarRec(raiz, dato) != null; }

    // ── Eliminar ──────────────────────────────────────────────────────────────

    public void eliminar(T dato) {
        if (!contiene(dato)) throw new ElementoNoEncontradoException(
            "No se puede eliminar: no existe un libro con ese codigo.");
        raiz = eliminarRec(raiz, dato);
    }

    private NodoArbol<T> eliminarRec(NodoArbol<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if      (cmp < 0) nodo.izquierdo = eliminarRec(nodo.izquierdo, dato);
        else if (cmp > 0) nodo.derecho   = eliminarRec(nodo.derecho,   dato);
        else {
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho   == null) return nodo.izquierdo;
            NodoArbol<T> sucesor = minimoNodo(nodo.derecho);
            nodo.dato   = sucesor.dato;
            nodo.derecho = eliminarRec(nodo.derecho, sucesor.dato);
        }
        return nodo;
    }

    private NodoArbol<T> minimoNodo(NodoArbol<T> nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    // ── Recorrido inorden ─────────────────────────────────────────────────────

    public Cola<T> inorden() {
        Cola<T> cola = new Cola<>();
        inordenRec(raiz, cola);
        return cola;
    }

    private void inordenRec(NodoArbol<T> nodo, Cola<T> cola) {
        if (nodo == null) return;
        inordenRec(nodo.izquierdo, cola);
        cola.enqueue(nodo.dato);
        inordenRec(nodo.derecho, cola);
    }

    // ── Utilidades ────────────────────────────────────────────────────────────

    public int contar() { return contarRec(raiz); }
    private int contarRec(NodoArbol<T> nodo) {
        if (nodo == null) return 0;
        return 1 + contarRec(nodo.izquierdo) + contarRec(nodo.derecho);
    }

    public boolean isEmpty() { return raiz == null; }
}

