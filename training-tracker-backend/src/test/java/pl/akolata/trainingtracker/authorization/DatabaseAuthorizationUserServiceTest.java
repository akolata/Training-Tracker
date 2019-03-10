package pl.akolata.trainingtracker.authorization;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.akolata.trainingtracker.IntegrationTestBase;
import pl.akolata.trainingtracker.Tags;
import pl.akolata.trainingtracker.shared.AppException;
import pl.akolata.trainingtracker.user.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag(Tags.INTEGRATION)
class DatabaseAuthorizationUserServiceTest extends IntegrationTestBase {

    private static final String USERNAME = "username";
    private static final String EMAIL = "test@email.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DatabaseAuthorizationUserService authorizationService;

    private SignUpRequest buildSignUpRequest(String username, String email) {
        SignUpRequest req = new SignUpRequest();
        req.setEmail(email);
        req.setFirstName("first");
        req.setLastName("last");
        req.setUsername(username);
        req.setPassword("password");

        return req;
    }

    private void setUpUserInDatabase() {
        User user = new User("f", "l", USERNAME, EMAIL, "p");
        Role role = new Role();
        role.setName(RoleName.ROLE_USER);

        userRepository.saveAndFlush(user);
        roleRepository.saveAndFlush(role);
    }

    @Nested
    @DisplayName("registerUser")
    class RegisterUser {

        @BeforeEach
        void setUp() {
            userRepository.deleteAll();
            roleRepository.deleteAll();
        }

        @AfterEach
        void tearDown() {
            userRepository.deleteAll();
            roleRepository.deleteAll();
        }

        @Test
        @DisplayName("when username is taken then throw exception")
        void register_whenUsernameIsTaken_thenThrowException() {
            // given
            setUpUserInDatabase();
            SignUpRequest req = buildSignUpRequest(USERNAME, "not" + EMAIL);

            // then
            assertThrows(UserRegistrationFailureException.class, () -> authorizationService.registerUser(req));
        }

        @Test
        @DisplayName("when email is taken then throw exception")
        void register_whenEmailIsTaken_thenThrowException() {
            // given
            setUpUserInDatabase();
            SignUpRequest req = buildSignUpRequest("not" + USERNAME, EMAIL);

            // then
            assertThrows(UserRegistrationFailureException.class, () -> authorizationService.registerUser(req));
        }

        @Test
        @DisplayName("user should be created in the database")
        void register_whenUsernameAndEmailAreUnique_thenUserWillBeCreated() throws UserRegistrationFailureException {
            // given
            setUpUserInDatabase();
            SignUpRequest req = buildSignUpRequest("not" + USERNAME, "not" + EMAIL);

            // when
            User user = authorizationService.registerUser(req);

            // then
            assertNotNull(user);
            assertEquals(req.getFirstName(), user.getFirstName());
            assertEquals(req.getLastName(), user.getLastName());
            assertEquals(req.getUsername(), user.getUsername());
            assertEquals(req.getEmail(), user.getEmail());
            assertNotNull(user.getPassword());
            assertNotEquals(req.getPassword(), user.getPassword());
        }

        @Test
        @DisplayName("when roles table is empty then throw exception")
        void register_whenRolesTableIsEmpty_thenThrowException() {
            // given
            roleRepository.deleteAll();
            SignUpRequest req = buildSignUpRequest("not" + USERNAME, "not" + EMAIL);

            // then
            assertThrows(AppException.class, () -> authorizationService.registerUser(req));
        }
    }
}
