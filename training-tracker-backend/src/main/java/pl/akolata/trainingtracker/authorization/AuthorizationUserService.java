package pl.akolata.trainingtracker.authorization;

import pl.akolata.trainingtracker.user.User;

public interface AuthorizationUserService {

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    User registerUser(SignUpRequest signUpRequest);
}
