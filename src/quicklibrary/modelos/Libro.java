package quicklibrary.modelos;

public class Libro implements Comparable<Libro> {

    private String      codigo;
    private String      titulo;
    private String      autor;
    private String      categoria;
    private int         anio;
    private EstadoLibro estado;

    public Libro(String codigo, String titulo, String autor, String categoria, int anio) {
        this.codigo    = codigo;
        this.titulo    = titulo;
        this.autor     = autor;
        this.categoria = categoria;
        this.anio      = anio;
        this.estado    = EstadoLibro.DISPONIBLE;
    }

    @Override
    public int compareTo(Libro otro) {
        return this.codigo.compareToIgnoreCase(otro.codigo);
    }

    // Getters
    public String      getCodigo()    { return codigo; }
    public String      getTitulo()    { return titulo; }
    public String      getAutor()     { return autor; }
    public String      getCategoria() { return categoria; }
    public int         getAnio()      { return anio; }
    public EstadoLibro getEstado()    { return estado; }

    // Setters
    public void setTitulo(String titulo)       { this.titulo    = titulo; }
    public void setAutor(String autor)         { this.autor     = autor; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setAnio(int anio)              { this.anio      = anio; }
    public void setEstado(EstadoLibro estado)  { this.estado    = estado; }

    public boolean estaDisponible() { return this.estado == EstadoLibro.DISPONIBLE; }

    @Override
    public String toString() {
        return String.format("| %-6s | %-38s | %-22s | %-15s | %4d | %-11s |",
            codigo, titulo, autor, categoria, anio, estado.getTexto());
    }
}

