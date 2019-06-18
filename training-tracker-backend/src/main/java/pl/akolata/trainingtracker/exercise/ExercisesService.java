package pl.akolata.trainingtracker.exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface ExercisesService {
    boolean exerciseExistsByNameAndType(String name, ExerciseType type);

    Page<Exercise> findExercises(Pageable pageable);

    Exercise createExercise(CreateExerciseCommand command);

    Exercise findExerciseById(Long id);
}
