package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {

    private final UserService userService;

    @Autowired
    public UserFacade(UserService userService) {
        this.userService = userService;
    }

    public User getUserById(Long id) {
        return userService.findUserById(id);
    }
}
