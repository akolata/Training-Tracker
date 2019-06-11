package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
class UserDatabaseService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserDatabaseService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long id) {
        Objects.requireNonNull(id);
        return userRepository.findById(id).orElse(null);
    }
}
