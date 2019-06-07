package pl.akolata.trainingtracker.gym;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@Slf4j
class GymController extends BaseApiController {
    private static final String GYM_URL = "/gyms";

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
                .path(BaseApiController.API_URL + GYM_URL + "/{id}")
                .buildAndExpand(gym.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(new ApiResponse<>(true, "Gym created"));
    }

    @GetMapping(
            path = GYM_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<Page<GymDto>>> getGyms(Pageable pageable) {
        Page<GymDto> gymsDTOsPage = gymService.findGyms(pageable != null ? pageable : getDefaultPageable());
        return ResponseEntity.ok(new ApiResponse<>(true, gymsDTOsPage));
    }

    @GetMapping(
            path = GYM_URL + "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<GymDto>> getGyms(@PathVariable Long id) {
        Optional<GymDto> gym = gymService.findGym(id);
        if (gym.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, gym.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = GymCreationFailureException.class)
    private ResponseEntity<ApiResponse<String>> handleRegistrationFailure(GymCreationFailureException e) {
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, e.getMessage()));
    }
}
