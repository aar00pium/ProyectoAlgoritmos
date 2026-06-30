package quicklibrary.validacion;

import quicklibrary.excepciones.DatoInvalidoException;

public class Validador {

    private Validador() {}

    public static void noVacio(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new DatoInvalidoException(
                "El campo '" + campo + "' no puede estar vacio.");
        }
    }

    public static void anioValido(int anio) {
        if (anio < 1450 || anio > 2100) {
            throw new DatoInvalidoException(
                "El anio '" + anio + "' no es valido. Debe estar entre 1450 y 2100.");
        }
    }

    public static void codigoValido(String codigo, String campo) {
        noVacio(codigo, campo);
        if (!codigo.trim().matches("[a-zA-Z0-9]+")) {
            throw new DatoInvalidoException(
                "El campo '" + campo + "' solo puede contener letras y numeros.");
        }
    }
}

