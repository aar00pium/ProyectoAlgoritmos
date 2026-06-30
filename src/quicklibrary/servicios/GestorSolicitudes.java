package quicklibrary.servicios;

import quicklibrary.estructuras.Cola;
import quicklibrary.modelos.SolicitudPrestamo;
import quicklibrary.validacion.Validador;

public class GestorSolicitudes {

    private final Cola<SolicitudPrestamo> cola = new Cola<>();
    private final GestorLibros gestorLibros;

    public GestorSolicitudes(GestorLibros gestorLibros) {
        this.gestorLibros = gestorLibros;
    }

    public void registrarSolicitud(String codigoEstudiante, String nombreEstudiante,
                                    String codigoLibro) {
        Validador.codigoValido(codigoEstudiante, "codigo estudiante");
        Validador.noVacio(nombreEstudiante,       "nombre estudiante");
        Validador.codigoValido(codigoLibro,       "codigo libro");

        gestorLibros.buscarLibro(codigoLibro);

        cola.enqueue(new SolicitudPrestamo(codigoEstudiante, nombreEstudiante, codigoLibro));
    }

    public SolicitudPrestamo verSiguiente() {
        return cola.peek();
    }

    public SolicitudPrestamo atenderSolicitud() {
        SolicitudPrestamo solicitud = cola.dequeue();
        gestorLibros.prestarLibro(solicitud.getCodigoLibro());
        return solicitud;
    }

    public void mostrarCola() {
        cola.mostrar();
    }

    public int contarPendientes() { return cola.size(); }

    public boolean isEmpty() { return cola.isEmpty(); }
}
