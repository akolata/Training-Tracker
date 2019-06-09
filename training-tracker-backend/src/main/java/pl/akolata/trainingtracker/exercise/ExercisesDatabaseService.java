package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
class ExercisesDatabaseService implements ExercisesService {

    private final ExercisesRepository exercisesRepository;

    @Autowired
    ExercisesDatabaseService(ExercisesRepository exercisesRepository) {
        this.exercisesRepository = exercisesRepository;
    }

    @Transactional
    @Override
    public Exercise createExercise(CreateExerciseCommand command) throws ExerciseCreationFailureException {
        Objects.requireNonNull(command);

        if (exercisesRepository.existsByName(command.getName())) {
            throw new ExerciseCreationFailureException("Exercise with name " + command.getName() + " already exists");
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
