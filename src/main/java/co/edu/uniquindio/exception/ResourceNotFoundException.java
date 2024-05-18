package co.edu.uniquindio.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String idRecurso) {
        super("No se encontro el ID " + idRecurso);
    }
}