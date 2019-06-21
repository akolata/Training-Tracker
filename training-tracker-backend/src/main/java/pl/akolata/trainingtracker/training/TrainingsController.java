package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;
import java.net.URI;

@RestController
class TrainingsController extends BaseApiController {

    private static final String TRAINING_URL = "/trainings/{trainingId}";
    private static final String TRAININGS_URL = "/trainings";

    private final TrainingApiService apiService;

    @Autowired
    TrainingsController(TrainingApiService apiService) {
        this.apiService = apiService;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(
            path = TRAININGS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<TrainingDto>> addTraining(@RequestBody @Valid CreateTrainingRequest request) {
        TrainingDto training = apiService.createTraining(request);
        URI location = getResourceLocation(TRAINING_URL, training.getId());
        return ResponseEntity
                .created(location)
                .body(ApiResponse.success(training));
    }
}
