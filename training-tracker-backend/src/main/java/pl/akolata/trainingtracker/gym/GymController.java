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

@RestController
@Slf4j
class GymController extends BaseApiController {
    private static final String GYM_URL = "/gyms";

    private final GymApiService gymService;

    @Autowired
    GymController(GymApiService gymService) {
        this.gymService = gymService;
    }

    @PostMapping(
            path = GYM_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<String>> createGym(@Valid @RequestBody CreateGymRequest createGymRequest) {
        CreateGymCommand createGymCommand = new CreateGymCommand(createGymRequest.getName());
        GymApiDto gym = gymService.createGym(createGymCommand);

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
    ResponseEntity<ApiResponse<Page<GymApiDto>>> getGyms(Pageable pageable) {
        Page<GymApiDto> gyms = gymService.findGyms(pageable != null ? pageable : getDefaultPageable());
        return ResponseEntity.ok(ApiResponse.success(gyms));
    }

    @GetMapping(
            path = GYM_URL + "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<GymApiDto>> getGym(@PathVariable Long id) {
        GymApiDto gym = gymService.findGymById(id);
        if (gym != null) {
            return ResponseEntity.ok(ApiResponse.success(gym));
        }
        return ResponseEntity.notFound().build();
    }
}
