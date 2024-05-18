package co.edu.uniquindio.exception;

public class LoginException {

    public static class UsuarioBloqueadoException extends Exception {
        public UsuarioBloqueadoException(String message) {
            super(message);
        }
    }


}
