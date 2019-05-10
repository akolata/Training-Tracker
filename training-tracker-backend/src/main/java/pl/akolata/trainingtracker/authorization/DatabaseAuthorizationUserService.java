package pl.akolata.trainingtracker.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.trainingtracker.shared.AppException;
import pl.akolata.trainingtracker.user.*;

import java.util.Collections;

@Service
class DatabaseAuthorizationUserService implements AuthorizationUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseAuthorizationUserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public User registerUser(SignUpRequest signUpRequest) throws UserRegistrationFailureException {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserRegistrationFailureException("Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserRegistrationFailureException("Email already in use!");
        }

        return userRepository.saveAndFlush(createUser(signUpRequest));
    }

    private User createUser(SignUpRequest signUpRequest) {
        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository
                .findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        return user;
    }
}
