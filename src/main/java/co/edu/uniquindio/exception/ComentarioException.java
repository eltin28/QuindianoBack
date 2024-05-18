package co.edu.uniquindio.exception;

public class ComentarioException {


    public class ComentarioValidationException extends Exception {
        public ComentarioValidationException(String message) {
            super(message);
        }
    }

    public class ComentarioAuthorizationException extends Exception {
        public ComentarioAuthorizationException(String message) {
            super(message);
        }
    }
}
