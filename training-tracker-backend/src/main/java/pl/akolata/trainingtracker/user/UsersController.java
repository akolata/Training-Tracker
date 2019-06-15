package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akolata.trainingtracker.shared.BaseApiController;
import pl.akolata.trainingtracker.training.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
class UsersController extends BaseApiController {

    private static final String USER_TRAININGS_URL = "/users/{userId}/trainings";
    private static final String USER_TRAINING_URL = "/users/{userId}/trainings/{trainingId}";
    private static final String USER_TRAININGS_SETS_URL = "/users/{userId}/trainings/{trainingId}/sets";
    private static final String USER_TRAININGS_SET_URL = "/users/{userId}/trainings/{trainingId}/sets/{setId}";

    private final UserFacade userFacade;
    private final TrainingsFacade trainingsFacade;
    private final TrainingEntityMapper trainingMapper;
    private final TrainingSetEntityMapper trainingSetEntityMapper;

    @Autowired
    UsersController(UserFacade userFacade, TrainingsFacade trainingsFacade, TrainingEntityMapper trainingMapper, TrainingSetEntityMapper trainingSetEntityMapper) {
        this.userFacade = userFacade;
        this.trainingsFacade = trainingsFacade;
        this.trainingMapper = trainingMapper;
        this.trainingSetEntityMapper = trainingSetEntityMapper;
    }

    @PostMapping(
            path = USER_TRAININGS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity addTraining(@PathVariable Long userId, @Valid @RequestBody CreateUserTrainingRequest request) {
        CreateTrainingCommand command = addTrainingRequestToCommand(request, userId);
        Training training = trainingsFacade.createTraining(command);
        userFacade.addTrainingToUser(training);

        URI location = getResourceLocation(USER_TRAINING_URL, userId, training.getId());
        TrainingApiDto trainingApiDto = trainingMapper.toApiDto(training);

        return ResponseEntity
                .created(location)
                .body(trainingApiDto);
    }

    @PostMapping(
            path = USER_TRAININGS_SETS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity addTrainingSet(@PathVariable Long userId, @PathVariable Long trainingId,
                                  @Valid @RequestBody CreateUserTrainingSetRequest request) {
        CreateTrainingSetCommand command = addTrainingSetRequestToCommand(request, trainingId);
        TrainingSet trainingSet = trainingsFacade.addSetToTraining(command);
        URI location = getResourceLocation(USER_TRAININGS_SET_URL, userId, trainingId, trainingSet.getId());
        TrainingSetDto trainingSetDto = trainingSetEntityMapper.toDto(trainingSet);

        return ResponseEntity
                .created(location)
                .body(trainingSetDto);
    }

    private CreateTrainingCommand addTrainingRequestToCommand(CreateUserTrainingRequest request, Long userId) {
        return new CreateTrainingCommand(
                request.getDate(),
                request.getGymId(),
                userId,
                request.getAdditionalInfo(),
                request.getName()
        );
    }

    private CreateTrainingSetCommand addTrainingSetRequestToCommand(CreateUserTrainingSetRequest request, Long trainingId) {
        return new CreateTrainingSetCommand(
                request.getExerciseId(),
                trainingId,
                request.getReps(),
                request.getWeightInKg(),
                request.getCalories(),
                request.getDurationInMinutes(),
                request.getDistanceInKm(),
                request.getAdditionalInfo()
        );
    }
}
