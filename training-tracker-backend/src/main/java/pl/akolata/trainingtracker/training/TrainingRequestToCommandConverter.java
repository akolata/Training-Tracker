package pl.akolata.trainingtracker.training;

import org.springframework.stereotype.Component;

@Component
class TrainingRequestToCommandConverter {

    CreateTrainingCommand createTrainingRequestToCommand(CreateTrainingRequest request) {
        return new CreateTrainingCommand(
                request.getDate(),
                request.getGymId(),
                request.getUserId(),
                request.getAdditionalInfo(),
                request.getName()
        );
    }
}
