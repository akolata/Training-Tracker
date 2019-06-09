package pl.akolata.trainingtracker.exercise;

import lombok.Value;

@Value
class CreateExerciseCommand {
    private final String name;
    private final ExerciseType type;

    Exercise toExercise() {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setType(type);
        return exercise;
    }
}
