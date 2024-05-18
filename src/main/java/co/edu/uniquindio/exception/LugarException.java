package co.edu.uniquindio.exception;

public class LugarException {

    public class LugarNotFoundException extends Exception {
        public LugarNotFoundException(String message) {
            super(message);
        }
    }

    public class LugarCreationException extends Exception {
        public LugarCreationException(String message) {
            super(message);
        }
    }

    public class LugarUpdateException extends Exception {
        public LugarUpdateException(String message) {
            super(message);
        }
    }

    public class LugarValidationException extends Exception {
        public LugarValidationException(String message) {
            super(message);
        }
    }

    public class LugarAuthorizationException extends Exception {
        public LugarAuthorizationException(String message) {
            super(message);
        }
    }
}
