package pl.akolata.trainingtracker.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.akolata.trainingtracker.BaseJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DatabaseUserDetailsServiceIntegrationTest extends BaseJpaTest {

    @Autowired
    private DatabaseUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("should throw exception if a user with provided username or email does not exist")
    void loadUserByUsername_whenUsersRepositoryIsEmpty_thenThrowException() {
        // given
        userRepository.deleteAll();

        // then
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("username");
        });
    }

    @Test
    @DisplayName("should load user by email")
    void loadUserByUsername_whenUserFoundByEmail_thenReturnSuchUser() {
        // given
        String email = "email";
        User user = new User("f", "l", "u", email, "p");
        user = userRepository.saveAndFlush(user);

        // when
        UserDetails foundUser = userDetailsService.loadUserByUsername(email);

        // then
        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    @DisplayName("should load user by username")
    void loadUserByUsername_whenUserFoundByUsername_thenReturnSuchUser() {
        // given
        String username = "username";
        User user = new User("f", "l", username, "e", "p");
        user = userRepository.saveAndFlush(user);

        // when
        UserDetails foundUser = userDetailsService.loadUserByUsername(username);

        // then
        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @Test
    @DisplayName("should throw exception if a user with provided id does not exist")
    void loadUserById_whenUserWithProvidedIdDoesNotExist_thenThrowException() {
        // given
        userRepository.deleteAll();

        // then
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserById(1L));
    }

    @Test
    @DisplayName("should load user by id")
    void loadUserById_whenUserFoundById_thenReturnSuchUser() {
        // given
        User user = new User("f", "l", "u", "e", "p");
        user = userRepository.saveAndFlush(user);

        // when
        UserDetails foundUser = userDetailsService.loadUserById(user.getId());

        // then
        assertEquals(user.getUsername(), foundUser.getUsername());
    }

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

}