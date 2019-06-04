package pl.akolata.trainingtracker.gym;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.akolata.trainingtracker.shared.ApiResponse;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(path = "/api")
@Slf4j
class GymController {

    private static final String GYM_URL = "/gym";

    private final GymService gymService;

    @Autowired
    GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping(
            path = GYM_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<String>> createGym(@Valid @RequestBody CreateGymRequest createGymRequest) throws GymCreationFailureException {
        CreateGymCommand createGymCommand = new CreateGymCommand(createGymRequest.getName());
        Gym gym = gymService.createGym(createGymCommand);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/gym/{id}")
                .buildAndExpand(gym.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse<>(true, "Gym created"));
    }

    @ExceptionHandler(value = GymCreationFailureException.class)
    private ResponseEntity<ApiResponse<String>> handleRegistrationFailure(GymCreationFailureException e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, e.getMessage()));
    }
}
