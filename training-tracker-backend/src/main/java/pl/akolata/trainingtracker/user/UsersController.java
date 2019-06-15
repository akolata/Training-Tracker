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

    private final UserFacade userFacade;
    private final TrainingsFacade trainingsFacade;
    private final TrainingEntityMapper trainingMapper;

    @Autowired
    UsersController(UserFacade userFacade, TrainingsFacade trainingsFacade, TrainingEntityMapper trainingMapper) {
        this.userFacade = userFacade;
        this.trainingsFacade = trainingsFacade;
        this.trainingMapper = trainingMapper;
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

        URI location = getResourceLocation(API_URL + USER_TRAINING_URL, userId, training.getId());
        TrainingApiDto trainingApiDto = trainingMapper.toApiDto(training);

        return ResponseEntity
                .created(location)
                .body(trainingApiDto);
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
}
