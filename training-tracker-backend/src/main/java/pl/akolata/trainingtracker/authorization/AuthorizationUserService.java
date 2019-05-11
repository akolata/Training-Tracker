package pl.akolata.trainingtracker.authorization;

import pl.akolata.trainingtracker.user.User;

interface AuthorizationUserService {
    User registerUser(SignUpRequest signUpRequest) throws UserRegistrationFailureException;
}
