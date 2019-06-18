package pl.akolata.trainingtracker.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;
import pl.akolata.trainingtracker.user.User;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
class AuthorizationController extends BaseApiController {

    private static final String SIGN_IN_URL = "/auth/sign-in";
    private static final String SIGN_UP_URL = "/auth/sign-up";

    private final AuthorizationApiService apiService;

    @Autowired
    AuthorizationController(AuthorizationApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping(
            path = SIGN_IN_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<JwtAuthenticationResponse> signIn(@Valid @RequestBody SignInRequest request) {
        String jwt = apiService.signIn(request);
        log.debug("User {} signed in", request.getUsernameOrEmail());

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(
            path = SIGN_UP_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<String>> signUp(@Valid @RequestBody SignUpRequest request) {
        User user = apiService.signUp(request);
        log.debug("User with email {} and username {} signed up", user.getEmail(), user.getUsername());

        URI location = getResourceLocation(USER_URL, user.getId());
        return ResponseEntity
                .created(location)
                .body(new ApiResponse<>(true, "User registered successfully"));
    }
}
