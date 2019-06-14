package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.gym.GymFacade;
import pl.akolata.trainingtracker.shared.ValidationResult;
import pl.akolata.trainingtracker.user.UserFacade;

import java.util.Objects;

@Service
class TrainingsValidationService {

    private final GymFacade gymFacade;
    private final UserFacade userFacade;

    @Autowired
    TrainingsValidationService(GymFacade gymFacade, UserFacade userFacade) {
        this.gymFacade = gymFacade;
        this.userFacade = userFacade;
    }

    ValidationResult validateCreateTrainingCommand(CreateTrainingCommand command) {
        Objects.requireNonNull(command);
        Long gymId = command.getGymId();
        Long userId = command.getUserId();

        if (gymId != null && gymFacade.getGymById(gymId) == null) {
            return ValidationResult.invalid("There is no gym with id " + gymId);
        }

        if (userId == null) {
            return ValidationResult.invalid("User id must not be null");
        }

        if (userFacade.getUserById(userId) == null) {
            return ValidationResult.invalid("There is no user with id " + userId);
        }

        return ValidationResult.valid();
    }
}
