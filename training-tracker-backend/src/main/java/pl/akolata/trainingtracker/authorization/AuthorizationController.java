package pl.akolata.trainingtracker.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.akolata.trainingtracker.security.JwtTokenProvider;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.ValidationErrorsResponse;
import pl.akolata.trainingtracker.user.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@Slf4j
class AuthorizationController {

    private final AuthorizationUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public AuthorizationController(
            AuthorizationUserService userService,
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws UserRegistrationFailureException {
        User user = userService.registerUser(signUpRequest);
        log.debug("User with email {} and username {} registered", user.getEmail(), user.getUsername());

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse<>(true, "User registered successfully"));
    }

    @ExceptionHandler(value = UserRegistrationFailureException.class)
    private ResponseEntity<ApiResponse<ValidationErrorsResponse>> handleRegistrationFailure(UserRegistrationFailureException e) {
        ValidationErrorsResponse errorsResponse = new ValidationErrorsResponse();
        List<String> errorsList = Collections.singletonList(e.getMessage());
        errorsResponse.getErrors().put(e.field, errorsList);
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, errorsResponse));
    }

}
