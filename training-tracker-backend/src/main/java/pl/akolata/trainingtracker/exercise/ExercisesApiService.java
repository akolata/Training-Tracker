package pl.akolata.trainingtracker.exercise;

import pl.akolata.trainingtracker.shared.exception.ResourceNotFoundException;

/**
 * Implementation with a generic type required by the API
 */
interface ExercisesApiService extends BaseExercisesService<ExerciseApiDto> {

    @Override
    ExerciseApiDto findExerciseById(Long id) throws ResourceNotFoundException;
}
