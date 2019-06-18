package pl.akolata.trainingtracker.exercise;

import org.springframework.stereotype.Component;

@Component
class ExercisesRequestsToCommandsConverter {

    CreateExerciseCommand createExerciseRequestsToCommand(CreateExerciseRequest request) {
        return new CreateExerciseCommand(
                request.getName(),
                request.getType()
        );
    }
}
