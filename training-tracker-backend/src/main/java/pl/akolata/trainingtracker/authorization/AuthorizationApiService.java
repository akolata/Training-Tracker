package pl.akolata.trainingtracker.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.user.SignUpCommand;
import pl.akolata.trainingtracker.user.User;
import pl.akolata.trainingtracker.user.UserFacade;

/**
 * Service responsible for /api/auth API operations
 */
@Service
class AuthorizationApiService {

    private final UserFacade userFacade;
    private final AuthorizationFacade authFacade;
    private final AuthRequestsToCommandConverter toCommandConverter;

    @Autowired
    AuthorizationApiService(
            UserFacade userFacade,
            AuthorizationFacade authFacade,
            AuthRequestsToCommandConverter toCommandConverter) {
        this.userFacade = userFacade;
        this.authFacade = authFacade;
        this.toCommandConverter = toCommandConverter;
    }

    User signUp(SignUpRequest request) {
        SignUpCommand command = toCommandConverter.signUpRequestToCommand(request);
        return userFacade.signUp(command);
    }

    String signIn(SignInRequest request) {
        SignInCommand command = toCommandConverter.signInRequestToCommand(request);
        return authFacade.signIn(command);
    }
}
