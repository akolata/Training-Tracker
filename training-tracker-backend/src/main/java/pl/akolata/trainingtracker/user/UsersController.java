package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.akolata.trainingtracker.shared.BaseApiController;
import pl.akolata.trainingtracker.training.CreateTrainingCommand;
import pl.akolata.trainingtracker.training.Training;
import pl.akolata.trainingtracker.training.TrainingsFacade;

import javax.validation.Valid;

@RestController
class UsersController extends BaseApiController {

    private static final String USER_TRAININGS_URL = "/users/{id}/trainings";
    private final UserFacade userFacade;
    private final TrainingsFacade trainingsFacade;

    @Autowired
    UsersController(UserFacade userFacade, TrainingsFacade trainingsFacade) {
        this.userFacade = userFacade;
        this.trainingsFacade = trainingsFacade;
    }

    @PostMapping(
            path = USER_TRAININGS_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity addTraining(@PathVariable Long id, @Valid @RequestBody CreateUserTrainingRequest request) {
        CreateTrainingCommand command = addTrainingRequestToCommand(request, id);
        Training training = trainingsFacade.createTraining(command);
        User user = userFacade.addTrainingToUser(training);
        // TODO return user
        return ResponseEntity.ok("");
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
