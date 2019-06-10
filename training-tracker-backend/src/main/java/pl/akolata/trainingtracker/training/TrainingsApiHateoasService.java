package pl.akolata.trainingtracker.training;

import org.springframework.stereotype.Service;

@Service
class TrainingsApiHateoasService implements TrainingsApiService {

    private final TrainingMapper trainingMapper;
    private final TrainingsService trainingsService;

    TrainingsApiHateoasService(TrainingMapper trainingMapper, TrainingsService trainingsService) {
        this.trainingMapper = trainingMapper;
        this.trainingsService = trainingsService;
    }

    @Override
    public TrainingApiDto createTraining(CreateTrainingCommand command) {
        Training training = trainingsService.createTraining(command);
        return mapToApiDto(training);
    }

    private TrainingApiDto mapToApiDto(Training training) {
        TrainingDto trainingDto = trainingMapper.toTrainingDto(training);
        return new TrainingApiDto(trainingDto);
    }
}
