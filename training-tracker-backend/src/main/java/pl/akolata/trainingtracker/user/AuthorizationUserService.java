package pl.akolata.trainingtracker.user;

interface AuthorizationUserService {
    User signUp(SignUpCommand command);
}
