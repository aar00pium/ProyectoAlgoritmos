package quicklibrary;

import quicklibrary.estructuras.Cola;
import quicklibrary.excepciones.CodigoDuplicadoException;
import quicklibrary.excepciones.ColaVaciaException;
import quicklibrary.excepciones.DatoInvalidoException;
import quicklibrary.excepciones.ElementoNoEncontradoException;
import quicklibrary.modelos.Libro;
import quicklibrary.modelos.SolicitudPrestamo;
import quicklibrary.servicios.GestorLibros;
import quicklibrary.servicios.GestorSolicitudes;
import java.util.Scanner;

public class Main {

    private static final GestorLibros      gestor    = new GestorLibros();
    private static final GestorSolicitudes gestorSol = new GestorSolicitudes(gestor);
    private static final Scanner           sc        = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerInt();
            try {
                ejecutar(opcion);
            } catch (DatoInvalidoException | CodigoDuplicadoException |
                     ElementoNoEncontradoException | ColaVaciaException e) {
                System.out.println("[Error] " + e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println("[Aviso] " + e.getMessage());
            }
        } while (opcion != 12);
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n========== QUICKLIBRARY ==========");
        System.out.println(" 1. Registrar libro");
        System.out.println(" 2. Mostrar libros");
        System.out.println(" 3. Buscar libro");
        System.out.println(" 4. Buscar libros por categoria");
        System.out.println(" 5. Modificar libro");
        System.out.println(" 6. Eliminar libro");
        System.out.println(" 7. Registrar solicitud de prestamo");
        System.out.println(" 8. Mostrar cola de solicitudes");
        System.out.println(" 9. Atender siguiente solicitud");
        System.out.println("10. Registrar devolucion");
        System.out.println("11. Mostrar reporte");
        System.out.println("12. Salir");
        System.out.print("Opcion: ");
    }

    private static void ejecutar(int opcion) {
        switch (opcion) {
            case 1:  registrarLibro();    break;
            case 2:  mostrarLibros();     break;
            case 3:  buscarLibro();       break;
            case 4:  buscarCategoria();   break;
            case 5:  modificarLibro();    break;
            case 6:  eliminarLibro();       break;
            case 7:  registrarSolicitud();  break;
            case 8:  mostrarCola();         break;
            case 9:  atenderSolicitud();    break;
            case 10: registrarDevolucion(); break;
            case 11: mostrarReporte();      break;
            case 12: System.out.println("Hasta luego."); break;
            default: System.out.println("Opcion invalida.");
        }
    }

    // opciones 1-6

    private static void registrarLibro() {
        System.out.print("Codigo    : "); String codigo    = sc.nextLine().trim();
        System.out.print("Titulo    : "); String titulo    = sc.nextLine().trim();
        System.out.print("Autor     : "); String autor     = sc.nextLine().trim();
        System.out.print("Categoria : "); String categoria = sc.nextLine().trim();
        System.out.print("Anio      : "); int    anio      = leerInt();
        gestor.registrarLibro(codigo, titulo, autor, categoria, anio);
        System.out.println("Libro registrado correctamente.");
    }

    private static void mostrarLibros() {
        System.out.println("1. Todos  2. Disponibles  3. Prestados");
        System.out.print("Opcion: ");
        int op = leerInt();
        Cola<Libro> lista = op == 2 ? gestor.listarDisponibles()
                          : op == 3 ? gestor.listarPrestados()
                          :           gestor.listarTodos();
        cabecera();
        if (lista.isEmpty()) System.out.println("Sin resultados.");
        else while (!lista.isEmpty()) System.out.println(lista.dequeue());
    }

    private static void buscarLibro() {
        System.out.println("1. Por codigo  2. Por titulo  3. Por autor");
        System.out.print("Opcion: ");
        int op = leerInt();
        if (op == 1) {
            System.out.print("Codigo: "); String c = sc.nextLine().trim();
            System.out.println(gestor.buscarLibro(c));
        } else if (op == 2) {
            System.out.print("Titulo: "); String t = sc.nextLine().trim();
            Cola<Libro> r = gestor.buscarPorTitulo(t);
            cabecera();
            if (r.isEmpty()) System.out.println("Sin resultados.");
            else while (!r.isEmpty()) System.out.println(r.dequeue());
        } else {
            System.out.print("Autor: "); String a = sc.nextLine().trim();
            Cola<Libro> r = gestor.buscarPorAutor(a);
            cabecera();
            if (r.isEmpty()) System.out.println("Sin resultados.");
            else while (!r.isEmpty()) System.out.println(r.dequeue());
        }
    }

    private static void buscarCategoria() {
        System.out.print("Categoria: "); String cat = sc.nextLine().trim();
        Cola<Libro> r = gestor.listarPorCategoria(cat);
        cabecera();
        if (r.isEmpty()) System.out.println("Sin resultados.");
        else while (!r.isEmpty()) System.out.println(r.dequeue());
    }

    private static void modificarLibro() {
        System.out.print("Codigo del libro: "); String codigo = sc.nextLine().trim();
        Libro libro = gestor.buscarLibro(codigo);
        System.out.println("Encontrado: " + libro);
        System.out.print("Nuevo titulo    (" + libro.getTitulo()    + "): "); String titulo    = sc.nextLine().trim();
        System.out.print("Nuevo autor     (" + libro.getAutor()     + "): "); String autor     = sc.nextLine().trim();
        System.out.print("Nueva categoria (" + libro.getCategoria() + "): "); String categoria = sc.nextLine().trim();
        System.out.print("Nuevo anio      (" + libro.getAnio()      + "): "); int    anio      = leerInt();
        gestor.actualizarLibro(codigo,
            titulo.isEmpty()    ? libro.getTitulo()    : titulo,
            autor.isEmpty()     ? libro.getAutor()     : autor,
            categoria.isEmpty() ? libro.getCategoria() : categoria,
            anio <= 0           ? libro.getAnio()      : anio);
        System.out.println("Libro actualizado correctamente.");
    }

    private static void eliminarLibro() {
        System.out.print("Codigo del libro a eliminar: "); String codigo = sc.nextLine().trim();
        gestor.eliminarLibro(codigo);
        System.out.println("Libro eliminado correctamente.");
    }

    // opciones 7-12

    private static void registrarSolicitud() {
        System.out.print("Codigo estudiante : "); String codEst = sc.nextLine().trim();
        System.out.print("Nombre estudiante : "); String nomEst = sc.nextLine().trim();
        System.out.print("Codigo libro      : "); String codLib = sc.nextLine().trim();
        gestorSol.registrarSolicitud(codEst, nomEst, codLib);
        System.out.println("Solicitud registrada correctamente.");
    }

    private static void mostrarCola() {
        if (gestorSol.isEmpty()) { System.out.println("No hay solicitudes pendientes."); return; }
        System.out.println("Cola de solicitudes:");
        gestorSol.mostrarCola();
    }

    private static void atenderSolicitud() {
        SolicitudPrestamo s = gestorSol.atenderSolicitud();
        System.out.println("Solicitud atendida:");
        System.out.println("  Estudiante : " + s.getNombreEstudiante() + " (" + s.getCodigoEstudiante() + ")");
        System.out.println("  Libro      : " + s.getCodigoLibro());
        System.out.println("  Fecha      : " + s.getFechaSolicitud());
        System.out.println("Libro marcado como PRESTADO.");
    }

    private static void registrarDevolucion() {
        System.out.print("Codigo del libro a devolver: "); String codigo = sc.nextLine().trim();
        gestor.devolverLibro(codigo);
        System.out.println("Devolucion registrada. Libro disponible nuevamente.");
    }

    private static void mostrarReporte() {
        int total      = gestor.getCatalogo().contar();
        int disponibles = gestor.listarDisponibles().size();
        int prestados   = gestor.contarPrestados();
        int pendientes  = gestorSol.contarPendientes();
        System.out.println("\n======== REPORTE GENERAL ========");
        System.out.println("Total de libros        : " + total);
        System.out.println("Libros disponibles     : " + disponibles);
        System.out.println("Libros prestados       : " + prestados);
        System.out.println("Solicitudes pendientes : " + pendientes);
        System.out.println("=================================");
    }

    // utilidades

    private static void cabecera() {
        System.out.println("+--------+----------------------------------------+------------------------+-----------------+------+-------------+");
        System.out.println("| Cod    | Titulo                                   | Autor                  | Categoria       | Anio | Estado      |");
        System.out.println("+--------+----------------------------------------+------------------------+-----------------+------+-------------+");
    }

    private static int leerInt() {
        try { return Integer.parseInt(sc.nextLine().trim()); }
        catch (NumberFormatException e) { return 0; }
    }
}
