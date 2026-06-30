package quicklibrary;

import quicklibrary.estructuras.ArbolBST;
import quicklibrary.estructuras.Cola;
import quicklibrary.modelos.Libro;

public class Main {

    public static void main(String[] args) {

        ArbolBST<Libro> catalogo = new ArbolBST<>();
        catalogo.insertar(new Libro("103","Introduccion a los Algoritmos","Thomas Cormen",  "Algoritmos",  2022));
        catalogo.insertar(new Libro("101","Programacion en Java",         "Herbert Schildt","Programacion",2022));
        catalogo.insertar(new Libro("105","Clean Code",                   "Robert C. Martin","Programacion",2020));
        catalogo.insertar(new Libro("102","Estructuras de Datos",         "Mark Allen Weiss","Computacion", 2021));
        catalogo.insertar(new Libro("104","Redes de Computadoras",        "Andrew Tanenbaum","Redes",       2021));

        System.out.println("=== Catalogo (inorden) ===");
        Cola<Libro> lista1 = catalogo.inorden();
        while (!lista1.isEmpty()) System.out.println("  " + lista1.dequeue());

        System.out.println("\n=== Buscar codigo 102 ===");
        System.out.println(catalogo.buscar(new Libro("102","","","",0)));

        System.out.println("\n=== Eliminar codigo 103 ===");
        catalogo.eliminar(new Libro("103","","","",0));
        Cola<Libro> lista2 = catalogo.inorden();
        while (!lista2.isEmpty()) System.out.println("  " + lista2.dequeue());

        System.out.println("\n=== Total libros: " + catalogo.contar() + " ===");

        Cola<String> solicitudes = new Cola<>();
        solicitudes.enqueue("Juan Quispe - libro 101");
        solicitudes.enqueue("Maria Lopez - libro 102");
        solicitudes.enqueue("Pedro Mamani - libro 104");

        System.out.println("\n=== Cola de solicitudes ===");
        solicitudes.mostrar();
        System.out.println("\nAtendiendo: " + solicitudes.dequeue());
        System.out.println("Siguiente: " + solicitudes.peek());
    }
}

