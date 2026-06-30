package quicklibrary.modelos;

import java.time.LocalDate;

public class SolicitudPrestamo {

    private String    codigoEstudiante;
    private String    nombreEstudiante;
    private String    codigoLibro;
    private LocalDate fechaSolicitud;

    public SolicitudPrestamo(String codigoEstudiante, String nombreEstudiante, String codigoLibro) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.codigoLibro      = codigoLibro;
        this.fechaSolicitud   = LocalDate.now();
    }

    public String    getCodigoEstudiante() { return codigoEstudiante; }
    public String    getNombreEstudiante() { return nombreEstudiante; }
    public String    getCodigoLibro()      { return codigoLibro; }
    public LocalDate getFechaSolicitud()   { return fechaSolicitud; }

    @Override
    public String toString() {
        return String.format("| %-10s | %-25s | %-8s | %s |",
            codigoEstudiante, nombreEstudiante, codigoLibro, fechaSolicitud);
    }
}
