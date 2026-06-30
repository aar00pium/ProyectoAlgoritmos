package quicklibrary.servicios;

import quicklibrary.estructuras.ArbolBST;
import quicklibrary.estructuras.Cola;
import quicklibrary.excepciones.CodigoDuplicadoException;
import quicklibrary.modelos.EstadoLibro;
import quicklibrary.modelos.Libro;
import quicklibrary.validacion.Validador;

public class GestorLibros {

    private final ArbolBST<Libro> catalogo = new ArbolBST<>();

    public GestorLibros() {
        cargarLibrosIniciales();
    }

    private void cargarLibrosIniciales() {
        catalogo.insertar(new Libro("A001", "Introduccion a los Algoritmos",       "Thomas Cormen",        "Algoritmos",      2022));
        catalogo.insertar(new Libro("A002", "Algoritmos en Java",                  "Robert Sedgewick",     "Algoritmos",      2021));
        catalogo.insertar(new Libro("A003", "El Arte de Programar",                "Donald Knuth",         "Algoritmos",      2020));
        catalogo.insertar(new Libro("P001", "Programacion en Java",                "Herbert Schildt",      "Programacion",    2022));
        catalogo.insertar(new Libro("P002", "Clean Code",                          "Robert C. Martin",     "Programacion",    2020));
        catalogo.insertar(new Libro("P003", "Refactoring",                         "Martin Fowler",        "Programacion",    2019));
        catalogo.insertar(new Libro("P004", "Effective Java",                      "Joshua Bloch",         "Programacion",    2021));
        catalogo.insertar(new Libro("P005", "Design Patterns",                     "Gang of Four",         "Programacion",    2018));
        catalogo.insertar(new Libro("E001", "Estructuras de Datos en Java",        "Mark Allen Weiss",     "Estructuras",     2021));
        catalogo.insertar(new Libro("E002", "Estructuras de Datos y Algoritmos",   "Michael Goodrich",     "Estructuras",     2020));
        catalogo.insertar(new Libro("E003", "Fundamentos de Estructuras de Datos", "Ellis Horowitz",       "Estructuras",     2019));
        catalogo.insertar(new Libro("C001", "Arquitectura de Computadoras",        "Andrew Tanenbaum",     "Computacion",     2021));
        catalogo.insertar(new Libro("C002", "Sistemas Operativos Modernos",        "Andrew Tanenbaum",     "Computacion",     2020));
        catalogo.insertar(new Libro("C003", "Compiladores",                        "Alfred Aho",           "Computacion",     2019));
        catalogo.insertar(new Libro("C004", "Organizacion de Computadoras",        "David Patterson",      "Computacion",     2021));
        catalogo.insertar(new Libro("R001", "Redes de Computadoras",               "Andrew Tanenbaum",     "Redes",           2021));
        catalogo.insertar(new Libro("R002", "TCP/IP Illustrated",                  "W. Richard Stevens",   "Redes",           2018));
        catalogo.insertar(new Libro("R003", "Redes y Comunicaciones",              "William Stallings",    "Redes",           2020));
        catalogo.insertar(new Libro("B001", "Fundamentos de Bases de Datos",       "Abraham Silberschatz", "Bases de Datos",  2022));
        catalogo.insertar(new Libro("B002", "MySQL en Profundidad",                "Paul DuBois",          "Bases de Datos",  2021));
        catalogo.insertar(new Libro("B003", "NoSQL Distilled",                     "Martin Fowler",        "Bases de Datos",  2019));
        catalogo.insertar(new Libro("M001", "Calculo Diferencial e Integral",      "James Stewart",        "Matematicas",     2020));
        catalogo.insertar(new Libro("M002", "Algebra Lineal",                      "Gilbert Strang",       "Matematicas",     2021));
        catalogo.insertar(new Libro("M003", "Matematica Discreta",                 "Kenneth Rosen",        "Matematicas",     2022));
        catalogo.insertar(new Libro("S001", "Ingenieria de Software",              "Ian Sommerville",      "Software",        2021));
        catalogo.insertar(new Libro("S002", "The Pragmatic Programmer",            "Andrew Hunt",          "Software",        2020));
        catalogo.insertar(new Libro("S003", "Software Architecture Patterns",      "Mark Richards",        "Software",        2019));
        catalogo.insertar(new Libro("I001", "Inteligencia Artificial",             "Stuart Russell",       "IA",              2022));
        catalogo.insertar(new Libro("I002", "Aprendizaje Automatico",              "Aurelien Geron",       "IA",              2021));
        catalogo.insertar(new Libro("I003", "Deep Learning",                       "Ian Goodfellow",       "IA",              2020));
    }

    public void registrarLibro(String codigo, String titulo, String autor,
                                String categoria, int anio) {
        Validador.codigoValido(codigo, "codigo");
        Validador.noVacio(titulo,    "titulo");
        Validador.noVacio(autor,     "autor");
        Validador.noVacio(categoria, "categoria");
        Validador.anioValido(anio);

        if (catalogo.contiene(new Libro(codigo, "", "", "", 0)))
            throw new CodigoDuplicadoException(
                "Ya existe un libro con el codigo '" + codigo + "'.");

        catalogo.insertar(new Libro(codigo, titulo, autor, categoria, anio));
    }

    public Libro buscarLibro(String codigo) {
        Validador.codigoValido(codigo, "codigo");
        return catalogo.buscar(new Libro(codigo, "", "", "", 0));
    }

    public Cola<Libro> listarTodos() {
        return catalogo.inorden();
    }

    public Cola<Libro> listarDisponibles() {
        Cola<Libro> resultado = new Cola<>();
        Cola<Libro> todos = catalogo.inorden();
        while (!todos.isEmpty()) {
            Libro l = todos.dequeue();
            if (l.estaDisponible()) resultado.enqueue(l);
        }
        return resultado;
    }

    public Cola<Libro> listarPorCategoria(String categoria) {
        Validador.noVacio(categoria, "categoria");
        Cola<Libro> resultado = new Cola<>();
        Cola<Libro> todos = catalogo.inorden();
        while (!todos.isEmpty()) {
            Libro l = todos.dequeue();
            if (l.getCategoria().equalsIgnoreCase(categoria.trim())) resultado.enqueue(l);
        }
        return resultado;
    }

    public void eliminarLibro(String codigo) {
        Validador.codigoValido(codigo, "codigo");
        catalogo.eliminar(new Libro(codigo, "", "", "", 0));
    }

    public void actualizarLibro(String codigo, String titulo, String autor,
                                 String categoria, int anio) {
        Validador.codigoValido(codigo, "codigo");
        Validador.noVacio(titulo,    "titulo");
        Validador.noVacio(autor,     "autor");
        Validador.noVacio(categoria, "categoria");
        Validador.anioValido(anio);

        Libro libro = catalogo.buscar(new Libro(codigo, "", "", "", 0));
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setAnio(anio);
    }

    public void prestarLibro(String codigoLibro) {
        Validador.codigoValido(codigoLibro, "codigo");
        Libro libro = catalogo.buscar(new Libro(codigoLibro, "", "", "", 0));
        if (!libro.estaDisponible())
            throw new IllegalStateException(
                "El libro '" + codigoLibro + "' ya esta prestado.");
        libro.setEstado(EstadoLibro.PRESTADO);
    }

    public void devolverLibro(String codigoLibro) {
        Validador.codigoValido(codigoLibro, "codigo");
        Libro libro = catalogo.buscar(new Libro(codigoLibro, "", "", "", 0));
        if (libro.estaDisponible())
            throw new IllegalStateException(
                "El libro '" + codigoLibro + "' no esta prestado.");
        libro.setEstado(EstadoLibro.DISPONIBLE);
    }

    public Cola<Libro> listarPrestados() {
        Cola<Libro> resultado = new Cola<>();
        Cola<Libro> todos = catalogo.inorden();
        while (!todos.isEmpty()) {
            Libro l = todos.dequeue();
            if (!l.estaDisponible()) resultado.enqueue(l);
        }
        return resultado;
    }

    public Cola<Libro> buscarPorTitulo(String titulo) {
        Validador.noVacio(titulo, "titulo");
        Cola<Libro> resultado = new Cola<>();
        Cola<Libro> todos = catalogo.inorden();
        while (!todos.isEmpty()) {
            Libro l = todos.dequeue();
            if (l.getTitulo().toLowerCase().contains(titulo.trim().toLowerCase()))
                resultado.enqueue(l);
        }
        return resultado;
    }

    public Cola<Libro> buscarPorAutor(String autor) {
        Validador.noVacio(autor, "autor");
        Cola<Libro> resultado = new Cola<>();
        Cola<Libro> todos = catalogo.inorden();
        while (!todos.isEmpty()) {
            Libro l = todos.dequeue();
            if (l.getAutor().toLowerCase().contains(autor.trim().toLowerCase()))
                resultado.enqueue(l);
        }
        return resultado;
    }

    public int contarPrestados() {
        int c = 0;
        Cola<Libro> todos = catalogo.inorden();
        while (!todos.isEmpty())
            if (!todos.dequeue().estaDisponible()) c++;
        return c;
    }

    public ArbolBST<Libro> getCatalogo() { return catalogo; }
}
