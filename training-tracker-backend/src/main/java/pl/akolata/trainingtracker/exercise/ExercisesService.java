package pl.akolata.trainingtracker.exercise;

interface ExercisesService {
    ExerciseDto createExercise(CreateExerciseCommand command) throws ExerciseCreationFailureException;
}
