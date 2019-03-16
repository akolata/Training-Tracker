package pl.akolata.trainingtracker.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.akolata.trainingtracker.Tags;
import pl.akolata.trainingtracker.user.User;
import pl.akolata.trainingtracker.user.UserAccountDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag(Tags.UNIT)
class UserPrincipalTest {

    @Test
    @DisplayName("should create user principal from user entity")
    void create() {
        // given
        User user = new User("John", "Smith", "johnsmith", "jogn@gmail.com", "password");

        // when
        UserPrincipal userPrincipal = UserPrincipal.create(user);

        // then
        assertEquals(user.getUsername(), userPrincipal.getUsername());
        assertEquals(user.getEmail(), userPrincipal.getEmail());
        assertEquals(user.getFirstName(), userPrincipal.getFirstName());
        assertEquals(user.getLastName(), userPrincipal.getLastName());
        assertEquals(user.getPassword(), userPrincipal.getPassword());

        UserAccountDetails accountDetails = user.getUserAccountDetails();
        assertEquals(!accountDetails.getAccountExpired(), userPrincipal.isAccountNonExpired());
        assertEquals(!accountDetails.getAccountLocked(), userPrincipal.isAccountNonLocked());
        assertEquals(!accountDetails.getCredentialsExpired(), userPrincipal.isCredentialsNonExpired());
        assertEquals(accountDetails.getEnabled(), userPrincipal.isEnabled());
    }
}