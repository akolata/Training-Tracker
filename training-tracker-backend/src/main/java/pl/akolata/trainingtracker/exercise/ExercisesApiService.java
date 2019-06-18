package pl.akolata.trainingtracker.exercise;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.shared.exception.ResourceNotFoundException;

@Service
class ExercisesApiService {

    private final ExercisesRequestsToCommandsConverter toCommandsConverter;
    private final ExerciseFacade exerciseFacade;
    private final ExerciseMapper exerciseMapper;

    ExercisesApiService(
            ExercisesRequestsToCommandsConverter toCommandsConverter,
            ExerciseFacade exerciseFacade,
            ExerciseMapper exerciseMapper) {
        this.toCommandsConverter = toCommandsConverter;
        this.exerciseFacade = exerciseFacade;
        this.exerciseMapper = exerciseMapper;
    }

    ExerciseDto createExercise(CreateExerciseRequest request) {
        CreateExerciseCommand command = toCommandsConverter.createExerciseRequestsToCommand(request);
        Exercise exercise = exerciseFacade.createExercise(command);
        return exerciseMapper.toExerciseDto(exercise);
    }

    Page<ExerciseDto> findExercises(Pageable pageable) {
        return exerciseFacade
                .findExercises(pageable)
                .map(exerciseMapper::toExerciseDto);
    }

    ExerciseDto findExerciseById(Long id) {
        Exercise exercise = exerciseFacade.findExerciseById(id);
        if (exercise == null) {
            throw new ResourceNotFoundException("Exercise with id " + id + " not found.");
        }
        return exerciseMapper.toExerciseDto(exercise);
    }
}
