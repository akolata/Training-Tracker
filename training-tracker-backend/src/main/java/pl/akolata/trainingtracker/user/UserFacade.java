package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.security.SecurityFacade;
import pl.akolata.trainingtracker.security.UserPrincipal;
import pl.akolata.trainingtracker.shared.exception.UserSignUpException;
import pl.akolata.trainingtracker.training.Training;

import java.util.Objects;

@Service
public class UserFacade {

    private final UserService userService;
    private final AuthorizationUserService authorizationUserService;
    private final SecurityFacade securityFacade;

    @Autowired
    public UserFacade(UserService userService, AuthorizationUserService authorizationUserService, SecurityFacade securityFacade) {
        this.userService = userService;
        this.authorizationUserService = authorizationUserService;
        this.securityFacade = securityFacade;
    }

    public User signUp(SignUpCommand command) throws UserSignUpException {
        return authorizationUserService.signUp(command);
    }

    public User getUserById(Long id) {
        Objects.requireNonNull(id);
        return userService.findUserById(id);
    }

    User addTrainingToUser(Training training) {
        Objects.requireNonNull(training);
        return userService.addTrainingToUser(training);
    }

    public User getCurrentUser() {
        UserPrincipal principal = securityFacade.getUserPrincipal();
        return getUserById(principal.getId());
    }
}
