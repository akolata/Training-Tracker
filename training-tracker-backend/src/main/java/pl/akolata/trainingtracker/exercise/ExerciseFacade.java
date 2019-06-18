package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.ValidationResult;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;

import java.util.Objects;

@Component
public class ExerciseFacade {

    private final ExercisesService exercisesService;
    private final ExercisesValidationService validationService;

    @Autowired
    public ExerciseFacade(ExercisesService exercisesService, ExercisesValidationService validationService) {
        this.exercisesService = exercisesService;
        this.validationService = validationService;
    }

    public Exercise findExerciseById(Long id) {
        Objects.requireNonNull(id);
        return exercisesService.findExerciseById(id);
    }

    Exercise createExercise(CreateExerciseCommand command) {
        ValidationResult validationResult = validationService.validateCreateExerciseCommand(command);
        if (validationResult.notValid()) {
            throw new ResourceCreationFailureException(validationResult.getErrorMsg());
        }
        return exercisesService.createExercise(command);
    }

    Page<Exercise> findExercises(Pageable pageable) {
        Objects.requireNonNull(pageable);
        return exercisesService.findExercises(pageable);
    }
}
