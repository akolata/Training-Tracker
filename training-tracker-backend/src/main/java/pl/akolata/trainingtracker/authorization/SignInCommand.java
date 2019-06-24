package pl.akolata.trainingtracker.authorization;

import lombok.Value;

@Value
class SignInCommand {
    private final String usernameOrEmail;
    private final String password;
}
