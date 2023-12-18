package med.souza.api.domain.exception;

public class IntegrityValidationException extends RuntimeException {

    public IntegrityValidationException(String message) {
        super(message);
    }
}
