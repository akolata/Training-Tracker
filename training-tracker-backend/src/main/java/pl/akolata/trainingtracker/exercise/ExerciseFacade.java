package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ExerciseFacade {

    private final ExercisesService exercisesService;

    @Autowired
    public ExerciseFacade(ExercisesService exercisesService) {
        this.exercisesService = exercisesService;
    }

    public Exercise findExerciseById(Long id) {
        Objects.requireNonNull(id);
        return exercisesService.findExerciseById(id);
    }
}
