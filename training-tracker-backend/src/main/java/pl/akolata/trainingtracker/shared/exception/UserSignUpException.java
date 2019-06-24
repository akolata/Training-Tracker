package pl.akolata.trainingtracker.shared.exception;


public class UserSignUpException extends RuntimeException {
    private final String field;

    public UserSignUpException(String field, String message) {
        super(message);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
