package pl.akolata.trainingtracker.exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Base interface for common operations on {@link Exercise} entity
 *
 * @param <T> actual result type - it might be a database entity, or an DTO
 */
interface BaseExercisesService<T> {
    T createExercise(CreateExerciseCommand command) throws ExerciseCreationFailureException;

    Page<T> findExercises(Pageable pageable);

    T findExerciseById(Long id);
}
