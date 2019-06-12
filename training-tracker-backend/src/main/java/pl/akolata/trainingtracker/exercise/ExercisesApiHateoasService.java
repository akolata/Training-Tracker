package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.shared.exception.ResourceNotFoundException;

@Service
class ExercisesApiHateoasService implements ExercisesApiService {

    private final ExercisesService exercisesService;
    private final ExerciseEntityMapper exerciseMapper;

    @Autowired
    ExercisesApiHateoasService(ExercisesService exercisesService, ExerciseEntityMapper exerciseMapper) {
        this.exercisesService = exercisesService;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public ExerciseApiDto createExercise(CreateExerciseCommand command) {
        Exercise exercise = exercisesService.createExercise(command);
        return exerciseMapper.toApiDto(exercise);
    }

    @Override
    public Page<ExerciseApiDto> findExercises(Pageable pageable) {
        return exercisesService.findExercises(pageable)
                .map(exerciseMapper::toApiDto);
    }

    @Override
    public ExerciseApiDto findExerciseById(Long id) {
        Exercise exercise = exercisesService.findExerciseById(id);

        if (exercise == null) {
            throw new ResourceNotFoundException("Exercise with id " + id + " not found.");
        }

        return exerciseMapper.toApiDto(exercise);
    }
}
