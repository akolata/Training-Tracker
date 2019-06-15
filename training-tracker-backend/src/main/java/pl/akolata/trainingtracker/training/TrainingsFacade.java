package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.ValidationResult;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;

@Component
public class TrainingsFacade {

    private final TrainingsService trainingsService;
    private final TrainingsValidationService validationService;

    @Autowired
    public TrainingsFacade(TrainingsValidationService validationService, TrainingsService trainingsService) {
        this.validationService = validationService;
        this.trainingsService = trainingsService;
    }

    public Training createTraining(CreateTrainingCommand command) {
        ValidationResult validationResult = validationService.validateCreateTrainingCommand(command);
        if (validationResult.notValid()) {
            throw new ResourceCreationFailureException(validationResult.getErrorMsg());
        }

        return trainingsService.createTraining(command);
    }

    public TrainingSet addSetToTraining(CreateTrainingSetCommand command) {
        ValidationResult validationResult = validationService.validateCreateTrainingSetCommand(command);
        if (validationResult.notValid()) {
            throw new ResourceCreationFailureException(validationResult.getErrorMsg());
        }

        return trainingsService.addTrainingSetToTraining(command);
    }
}
