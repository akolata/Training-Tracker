package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;
import pl.akolata.trainingtracker.training.CreateTrainingCommand;
import pl.akolata.trainingtracker.training.CreateTrainingSetCommand;
import pl.akolata.trainingtracker.training.TrainingApiDto;
import pl.akolata.trainingtracker.training.TrainingSetDto;

import javax.validation.Valid;
import java.net.URI;

@RestController
class UsersController extends BaseApiController {

    private static final String USER_TRAININGS_URL = "/users/{userId}/trainings";
    private static final String USER_TRAINING_URL = "/users/{userId}/trainings/{trainingId}";
    private static final String USER_TRAININGS_SETS_URL = "/users/{userId}/trainings/{trainingId}/sets";
    private static final String USER_TRAININGS_SET_URL = "/users/{userId}/trainings/{trainingId}/sets/{setId}";

    private final UserApiRequestsToCommandsConverter toCommandsConverter;
    private final UserApiOperationsService apiOperationsService;

    @Autowired
    UsersController(UserApiRequestsToCommandsConverter toCommandsConverter, UserApiOperationsService apiOperationsService) {
        this.toCommandsConverter = toCommandsConverter;
        this.apiOperationsService = apiOperationsService;
    }

    @PostMapping(
            path = USER_TRAININGS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<TrainingApiDto>> addTraining(@PathVariable Long userId, @Valid @RequestBody CreateUserTrainingRequest request) {
        CreateTrainingCommand command = toCommandsConverter.addTrainingRequestToCommand(request, userId);
        TrainingApiDto trainingApiDto = apiOperationsService.addTrainingToUser(command, userId);
        URI location = getResourceLocation(USER_TRAINING_URL, userId, trainingApiDto.getId());

        return ResponseEntity
                .created(location)
                .body(ApiResponse.success(trainingApiDto));
    }

    @GetMapping(
            path = USER_TRAININGS_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<Page<TrainingApiDto>>> getUserTrainings(@PathVariable Long userId, Pageable pageable) {
        Page<TrainingApiDto> trainingsPage = apiOperationsService.getUserTrainings(userId, getPageable(pageable));

        return ResponseEntity
                .ok(ApiResponse.success(trainingsPage));
    }

    @PostMapping(
            path = USER_TRAININGS_SETS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<TrainingSetDto>> addTrainingSet(@PathVariable Long userId, @PathVariable Long trainingId,
                                                               @Valid @RequestBody CreateUserTrainingSetRequest request) {
        CreateTrainingSetCommand command = toCommandsConverter.addTrainingSetRequestToCommand(request, trainingId);
        TrainingSetDto trainingSetDto = apiOperationsService.addTrainingSetToUserTraining(command);
        URI location = getResourceLocation(USER_TRAININGS_SET_URL, userId, trainingId, trainingSetDto.getId());

        return ResponseEntity
                .created(location)
                .body(ApiResponse.success(trainingSetDto));
    }
}
