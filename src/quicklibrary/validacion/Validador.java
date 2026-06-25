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
}

