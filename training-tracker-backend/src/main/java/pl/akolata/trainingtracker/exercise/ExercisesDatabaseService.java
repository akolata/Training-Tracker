package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
class ExercisesDatabaseService implements ExercisesService {

    private final ExercisesRepository exercisesRepository;
    private final ExerciseMapper exerciseMapper;

    @Autowired
    ExercisesDatabaseService(ExercisesRepository exercisesRepository, ExerciseMapper exerciseMapper) {
        this.exercisesRepository = exercisesRepository;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public ExerciseDto createExercise(CreateExerciseCommand command) throws ExerciseCreationFailureException {
        Objects.requireNonNull(command);

        if (exercisesRepository.existsByName(command.getName())) {
            throw new ExerciseCreationFailureException("Exercise with name " + command.getName() + " already exists");
        }

        Exercise exercise = exercisesRepository.saveAndFlush(command.toExercise());
        return exerciseMapper.fromEntity(exercise);
    }
}
