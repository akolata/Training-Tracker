package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;

import java.util.Objects;

@Service
class ExercisesDatabaseService implements ExercisesService {

    private static final String DUPLICATED_EXERCISE_MSG = "Exercise with name '%s' and type %s already exists";

    private final ExercisesRepository exercisesRepository;

    @Autowired
    ExercisesDatabaseService(ExercisesRepository exercisesRepository) {
        this.exercisesRepository = exercisesRepository;
    }

    @Transactional
    @Override
    public Exercise createExercise(CreateExerciseCommand command) {
        Objects.requireNonNull(command);

        if (exercisesRepository.existsByNameAndType(command.getName(), command.getType())) {
            throw new ResourceCreationFailureException(
                    String.format(DUPLICATED_EXERCISE_MSG, command.getName(), command.getType()));
        }

        return exercisesRepository.saveAndFlush(command.toExercise());
    }

    @Override
    public Page<Exercise> findExercises(Pageable pageable) {
        Objects.requireNonNull(pageable);
        return exercisesRepository.findAll(pageable);
    }

    @Override
    public Exercise findExerciseById(Long id) {
        Objects.requireNonNull(id);
        return exercisesRepository.findById(id).orElse(null);
    }
}
