package pl.akolata.trainingtracker.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import pl.akolata.trainingtracker.BaseJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryIntegrationTest extends BaseJpaTest {

    private static final String USERNAME = "username";
    private static final String EMAIL = "email";

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("should not allow to create user with null first name")
    void save_whenFirstNameIsNull_thenThrowException() {
        // given
        User user = new User(null, "lastName", "username", "email", "password");

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(user));
    }

    @Test
    @DisplayName("should not allow to create user with null last name")
    void save_whenLastNameIsNull_thenThrowException() {
        // given
        User user = new User("firstName", null, "username", "email", "password");

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(user));
    }

    @Test
    @DisplayName("should not allow to create user with null username")
    void save_whenUsernameIsNull_thenThrowException() {
        // given
        User user = new User("firstName", "lastName", null, "email", "password");

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(user));
    }

    @Test
    @DisplayName("should not allow to create user with null email")
    void save_whenEmailIsNull_thenThrowException() {
        // given
        User user = new User("firstName", "lastName", "username", null, "password");

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(user));
    }

    @Test
    @DisplayName("should not allow to create user with null password")
    void save_whenPasswordIsNull_thenThrowException() {
        // given
        User user = new User("firstName", "lastName", "username", "email", null);

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(user));
    }

    @Test
    @DisplayName("created user should has fields from base entity")
    void save_whenUserIsCreated_fieldsFromBaseEntityAreNotNull() {
        // given
        User user = createUser(USERNAME, EMAIL);

        // when
        User savedUser = userRepository.saveAndFlush(user);

        // then
        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getUuid());
        assertNotNull(savedUser.getUpdatedAt());
        assertNotNull(savedUser.getCreatedAt());
        assertNotNull(savedUser.getVersion());
    }

    @Test
    @DisplayName("should return true if a user with provided username exists")
    void existsByUsername_whenUserExists_shouldReturnTrue() {
        // given
        User user = createUser(USERNAME, EMAIL);
        userRepository.saveAndFlush(user);

        // when
        boolean existsByUsername = userRepository.existsByUsername(USERNAME);

        // then
        assertTrue(existsByUsername);
    }

    @Test
    @DisplayName("should return false if a user with provided username does not exist")
    void existsByUsername_whenUserDoesNotExist_shouldReturnFalse() {
        // given
        User user = createUser(USERNAME, EMAIL);
        userRepository.saveAndFlush(user);

        // when
        boolean existsByUsername = userRepository.existsByUsername("NOT" + USERNAME);

        // then
        assertFalse(existsByUsername);
    }

    @Test
    @DisplayName("should return true if a user with provided email exists")
    void existsByEmail_whenUserExists_shouldReturnTrue() {
        // given
        User user = createUser(USERNAME, EMAIL);
        userRepository.saveAndFlush(user);

        // when
        boolean existsByUsername = userRepository.existsByEmail(EMAIL);

        // then
        assertTrue(existsByUsername);
    }

    @Test
    @DisplayName("should return false if a user with provided email does not exist")
    void existsByEmail_whenUserDoesNotExist_shouldReturnFalse() {
        // given
        User user = createUser(USERNAME, EMAIL);
        userRepository.saveAndFlush(user);

        // when
        boolean existsByUsername = userRepository.existsByEmail("NOT" + EMAIL);

        // then
        assertFalse(existsByUsername);
    }

    @Test
    @DisplayName("should return user either by username or by email if one exist")
    void testFindByUsernameOrEmail() {
        // given
        User user = createUser(USERNAME, EMAIL);
        user = userRepository.saveAndFlush(user);

        // when
        Optional<User> byExistingUsernameAndEmail = userRepository.findByUsernameOrEmail(USERNAME, EMAIL);
        Optional<User> byExistingEmail = userRepository.findByUsernameOrEmail("NOT" + USERNAME, EMAIL);
        Optional<User> byExistingUsername = userRepository.findByUsernameOrEmail(USERNAME, "NOT" + EMAIL);
        Optional<User> byNotExistingParams = userRepository.findByUsernameOrEmail("NOT" + USERNAME, "NOT" + EMAIL);

        // then
        assertTrue(byExistingUsernameAndEmail.isPresent());
        assertEquals(user, byExistingUsernameAndEmail.get());

        assertTrue(byExistingEmail.isPresent());
        assertEquals(user, byExistingEmail.get());

        assertTrue(byExistingUsername.isPresent());
        assertEquals(user, byExistingUsername.get());

        assertFalse(byNotExistingParams.isPresent());
    }

    @Test
    @DisplayName("should not allow to save user with existing username")
    void save_whenUsermameIsNotUnique_shouldThrowException() {
        // given
        User user = createUser(USERNAME, EMAIL);
        User userWithTheSameUsername = createUser(USERNAME, "OTHER" + EMAIL);
        userRepository.saveAndFlush(user);

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(userWithTheSameUsername));
    }

    @Test
    @DisplayName("should not allow to save user with existing email")
    void save_whenEmailIsNotUnique_shouldThrowException() {
        // given
        User user = createUser(USERNAME, EMAIL);
        User userWithTheSameUsername = createUser("OTHER" + USERNAME, EMAIL);
        userRepository.saveAndFlush(user);

        // when
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.saveAndFlush(userWithTheSameUsername));
    }

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    private User createUser(String username, String email) {
        return new User("testFirstName", "testLastName", username, email, "testPassword");
    }
}