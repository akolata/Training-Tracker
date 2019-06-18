package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.trainingtracker.shared.exception.AppException;
import pl.akolata.trainingtracker.shared.exception.UserSignUpException;

import java.util.Collections;

@Service
class DatabaseAuthorizationUserService implements AuthorizationUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    DatabaseAuthorizationUserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public User signUp(SignUpCommand command) throws UserSignUpException {
        if (userRepository.existsByUsername(command.getUsername())) {
            throw new UserSignUpException("Username", "Username is already taken!");
        }

        if (userRepository.existsByEmail(command.getEmail())) {
            throw new UserSignUpException("Email", "Email already in use!");
        }

        return userRepository.saveAndFlush(createUser(command));
    }

    private User createUser(SignUpCommand command) {
        User user = new User(command.getFirstName(), command.getLastName(), command.getUsername(),
                command.getEmail(), command.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository
                .findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        return user;
    }
}
