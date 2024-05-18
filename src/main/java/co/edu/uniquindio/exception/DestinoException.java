package co.edu.uniquindio.exception;
public class DestinoException {
    public class DestinoNoEncontradoException extends Exception {
        public DestinoNoEncontradoException(String message) {
            super(message);
        }
    }

    public class RutaNoDisponibleException extends Exception {
        public RutaNoDisponibleException(String message) {
            super(message);
        }
    }

    public class DestinoInvalidoException extends Exception {
        public DestinoInvalidoException(String message) {
            super(message);
        }
    }

    public class DestinoNoAlcanzableException extends Exception {
        public DestinoNoAlcanzableException(String message) {
            super(message);
        }
    }

    public class DestinoRepetidoException extends Exception {
        public DestinoRepetidoException(String message) {
            super(message);
        }
    }

}
