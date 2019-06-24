package pl.akolata.trainingtracker.authorization;

import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.user.SignUpCommand;

@Component
class AuthRequestsToCommandConverter {

    SignUpCommand signUpRequestToCommand(SignUpRequest request) {
        return new SignUpCommand(
                request.getFirstName(),
                request.getLastName(),
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );
    }

    SignInCommand signInRequestToCommand(SignInRequest request) {
        return new SignInCommand(
                request.getUsernameOrEmail(),
                request.getPassword()
        );
    }
}
