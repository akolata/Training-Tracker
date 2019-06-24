package pl.akolata.trainingtracker.gym;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
class GymController extends BaseApiController {
    private static final String GYM_URL = "/gyms/{gymId}";
    private static final String GYMS_URL = "/gyms";

    private final GymApiService apiService;

    @Autowired
    GymController(GymApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping(
            path = GYMS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<GymDto>> addGym(@Valid @RequestBody CreateGymRequest request) {
        GymDto gym = apiService.createGym(request);
        URI location = getResourceLocation(GYM_URL, gym.getId());
        log.debug("Gym with name {} created", gym.getName());

        return ResponseEntity
                .created(location)
                .body(new ApiResponse<>(true, gym));
    }

    @GetMapping(
            path = GYMS_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<Page<GymDto>>> getGyms(Pageable pageable) {
        Page<GymDto> gym = apiService.findGyms(pageable != null ? pageable : getDefaultPageable());
        return ResponseEntity
                .ok(ApiResponse.success(gym));
    }

    @GetMapping(
            path = GYM_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<GymDto>> getGym(@PathVariable Long gymId) {
        GymDto gym = apiService.findGymById(gymId);
        return ResponseEntity
                .ok(ApiResponse.success(gym));
    }
}
