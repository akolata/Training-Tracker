package pl.akolata.trainingtracker.user;

import lombok.Value;

@Value
public class SignUpCommand {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String email;
    private final String password;
}
