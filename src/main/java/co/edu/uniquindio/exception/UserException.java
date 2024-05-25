package co.edu.uniquindio.exception;

public class UserException {

    public static class UsuarioBloqueadoException extends Exception {
        public UsuarioBloqueadoException(String message) {
            super(message);
        }
    }

    public static class CuentaNoValidadaException extends Exception {
        public CuentaNoValidadaException(String message) {
            super(message);
        }
    }

}
