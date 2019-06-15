package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.ValidationResult;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;

import java.util.Objects;

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

    public Page<Training> findTraingsByUserId(Long userId, Pageable pageable) {
        Objects.requireNonNull(userId);
        return trainingsService.findTrainingsByUserId(userId, pageable);
    }
}
