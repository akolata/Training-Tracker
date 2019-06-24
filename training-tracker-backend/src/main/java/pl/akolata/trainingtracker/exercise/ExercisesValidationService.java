package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.ValidationResult;

import java.util.Objects;

@Component
class ExercisesValidationService {

    private static final String DUPLICATED_EXERCISE_MSG = "Exercise with name '%s' and type %s already exists";

    private final ExercisesService exercisesService;

    @Autowired
    ExercisesValidationService(ExercisesService exercisesService) {
        this.exercisesService = exercisesService;
    }

    ValidationResult validateCreateExerciseCommand(CreateExerciseCommand command) {
        Objects.requireNonNull(command);

        if (exercisesService.exerciseExistsByNameAndType(command.getName(), command.getType())) {
            return ValidationResult.invalid(
                    String.format(DUPLICATED_EXERCISE_MSG, command.getName(), command.getType())
            );
        }
        return ValidationResult.valid();
    }
}
