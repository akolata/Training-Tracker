package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class TrainingApiService {

    private final TrainingMapper trainingMapper;
    private final TrainingsFacade trainingsFacade;
    private final TrainingRequestToCommandConverter toCommandConverter;

    @Autowired
    TrainingApiService(
            TrainingMapper trainingMapper,
            TrainingsFacade trainingsFacade,
            TrainingRequestToCommandConverter toCommandConverter) {
        this.trainingMapper = trainingMapper;
        this.trainingsFacade = trainingsFacade;
        this.toCommandConverter = toCommandConverter;
    }

    TrainingDto createTraining(CreateTrainingRequest request) {
        CreateTrainingCommand command = toCommandConverter.createTrainingRequestToCommand(request);
        Training training = trainingsFacade.createTraining(command);
        return trainingMapper.toTrainingDto(training);
    }
}
