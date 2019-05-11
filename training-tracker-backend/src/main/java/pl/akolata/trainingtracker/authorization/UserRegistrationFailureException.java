package pl.akolata.trainingtracker.authorization;


class UserRegistrationFailureException extends Exception {
    final String field;

    UserRegistrationFailureException(String field, String message) {
        super(message);
        this.field = field;
    }
}
