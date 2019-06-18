package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.exercise.ExerciseFacade;
import pl.akolata.trainingtracker.gym.GymFacade;
import pl.akolata.trainingtracker.shared.ValidationResult;
import pl.akolata.trainingtracker.user.User;
import pl.akolata.trainingtracker.user.UserFacade;

import java.util.Arrays;
import java.util.Objects;

@Service
class TrainingsValidationService {

    private final GymFacade gymFacade;
    private final UserFacade userFacade;
    private final ExerciseFacade exerciseFacade;
    private final TrainingsService trainingsService;

    @Autowired
    TrainingsValidationService(
            GymFacade gymFacade,
            UserFacade userFacade,
            ExerciseFacade exerciseFacade,
            TrainingsService trainingsService) {
        this.gymFacade = gymFacade;
        this.userFacade = userFacade;
        this.exerciseFacade = exerciseFacade;
        this.trainingsService = trainingsService;
    }

    ValidationResult validateCreateTrainingCommand(CreateTrainingCommand command) {
        Objects.requireNonNull(command);
        Long gymId = command.getGymId();
        Long userId = command.getUserId();

        if (gymId != null && gymFacade.findGymById(gymId) == null) {
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

    ValidationResult validateCreateTrainingSetCommand(CreateTrainingSetCommand command) {
        Objects.requireNonNull(command);
        Long exerciseId = command.getExerciseId();
        Long trainingId = command.getTrainingId();

        if (exerciseId == null) {
            return ValidationResult.invalid("Exercise id must not be null");
        }

        if (trainingId == null) {
            return ValidationResult.invalid("Training id must not be null");
        }

        Training training = trainingsService.findTrainingById(trainingId);
        if (training == null) {
            return ValidationResult.invalid("Training with id " + trainingId + " not found");
        }

        User user = training.getUser();
        if (user == null) {
            return ValidationResult.invalid("Training with id " + trainingId + " is not owned by any user");
        }

        // TODO it probably shouldn't be validated for an admin
        if (!user.equals(userFacade.getCurrentUser())) {
            return ValidationResult.invalid("Current user is not an owner of this training");
        }

        if (exerciseFacade.findExerciseById(exerciseId) == null) {
            return ValidationResult.invalid("Exercise with id " + exerciseId + " not found");
        }

        if (allCommandArgumentsOtherThanIdsAreNull(command)) {
            return ValidationResult.invalid("None information about training has been provided");
        }

        return ValidationResult.valid();
    }

    private boolean allCommandArgumentsOtherThanIdsAreNull(CreateTrainingSetCommand command) {
        return allArgumentsAreNull(
                command.getReps(),
                command.getWeight(),
                command.getCalories(),
                command.getDurationInMinutes(),
                command.getDistanceInKm(),
                command.getAdditionalInfo()
        );
    }

    private boolean allArgumentsAreNull(Object... args) {
        return Arrays
                .stream(args)
                .allMatch(Objects::isNull);
    }
}
