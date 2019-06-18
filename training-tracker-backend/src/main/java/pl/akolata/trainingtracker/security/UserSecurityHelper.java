package pl.akolata.trainingtracker.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserSecurityHelper {

    public boolean hasUserId(Authentication authentication, Long userId) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return principal.getId().equals(userId);
    }
}
