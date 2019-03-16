package pl.akolata.trainingtracker.authorization;


class UserRegistrationFailureException extends Exception {

    UserRegistrationFailureException(String message) {
        super(message);
    }
}
