package quicklibrary.modelos;

public class Libro implements Comparable<Libro> {

    private String codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private String estado;

    public Libro(String codigo, String titulo, String autor, String categoria, int anio, String estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.estado = estado;
    }

    @Override
    public int compareTo(Libro otro) {
        return this.codigo.compareTo(otro.codigo);
    }

    public String getCodigo() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public int getAnio() { return anio; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return codigo + " | " + titulo + " | " + autor + " | " + categoria + " | " + anio + " | " + estado;
    }
}

