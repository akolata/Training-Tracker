package pl.akolata.trainingtracker.training;

import org.springframework.stereotype.Service;

@Service
class TrainingsApiHateoasService implements TrainingsApiService {

    private final TrainingEntityMapper trainingMapper;
    private final TrainingsService trainingsService;

    TrainingsApiHateoasService(TrainingEntityMapper trainingMapper, TrainingsService trainingsService) {
        this.trainingMapper = trainingMapper;
        this.trainingsService = trainingsService;
    }

    @Override
    public TrainingApiDto createTraining(CreateTrainingCommand command) {
        Training training = trainingsService.createTraining(command);
        return trainingMapper.toApiDto(training);
    }
}
