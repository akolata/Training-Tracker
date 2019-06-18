package pl.akolata.trainingtracker.user;

import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.training.CreateTrainingCommand;
import pl.akolata.trainingtracker.training.CreateTrainingSetCommand;

@Component
class UserApiRequestsToCommandsConverter {

    CreateTrainingCommand addTrainingRequestToCommand(CreateUserTrainingRequest request, Long userId) {
        return new CreateTrainingCommand(
                request.getDate(),
                request.getGymId(),
                userId,
                request.getAdditionalInfo(),
                request.getName()
        );
    }

    CreateTrainingSetCommand addTrainingSetRequestToCommand(CreateUserTrainingSetRequest request, Long trainingId) {
        return new CreateTrainingSetCommand(
                request.getExerciseId(),
                trainingId,
                request.getReps(),
                request.getWeightInKg(),
                request.getCalories(),
                request.getDurationInMinutes(),
                request.getDistanceInKm(),
                request.getAdditionalInfo()
        );
    }
}
