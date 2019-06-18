package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.security.SecurityFacade;
import pl.akolata.trainingtracker.security.UserPrincipal;
import pl.akolata.trainingtracker.training.Training;

import java.util.Objects;

@Service
class UserDatabaseService implements UserService {

    private final SecurityFacade securityFacade;
    private final UserRepository userRepository;

    @Autowired
    UserDatabaseService(SecurityFacade securityFacade, UserRepository userRepository) {
        this.securityFacade = securityFacade;
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addTrainingToUser(Training training) {
        UserPrincipal principal = securityFacade.getUserPrincipal();
        User user = findUserById(principal.getId());
        user.addTraining(training);
        return userRepository.saveAndFlush(user);
    }
}
