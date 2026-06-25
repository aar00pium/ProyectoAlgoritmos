package quicklibrary.modelos;

public enum EstadoLibro {
    DISPONIBLE("Disponible"),
    PRESTADO("Prestado");

    private final String texto;

    EstadoLibro(String texto) { this.texto = texto; }

    public String getTexto() { return texto; }

    @Override
    public String toString() { return texto; }
}

