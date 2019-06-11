package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;
import java.net.URI;

@RestController
class TrainingsController extends BaseApiController {

    private static final String TRAININGS_URL = "/trainings";

    private final TrainingsApiService trainingsService;

    @Autowired
    TrainingsController(TrainingsApiService trainingsService) {
        this.trainingsService = trainingsService;
    }

    @PostMapping(
            path = TRAININGS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<TrainingApiDto>> addTraining(@RequestBody @Valid CreateTrainingRequest request) {
        CreateTrainingCommand command = createCommand(request);
        TrainingApiDto training = trainingsService.createTraining(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(BaseApiController.API_URL + TRAININGS_URL + "/{id}")
                .buildAndExpand(training.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(ApiResponse.success(training));
    }

    private CreateTrainingCommand createCommand(CreateTrainingRequest request) {
        return new CreateTrainingCommand(
                request.getDate(),
                request.getGymId(),
                request.getUserId(),
                request.getAdditionalInfo(),
                request.getName()
        );
    }
}
