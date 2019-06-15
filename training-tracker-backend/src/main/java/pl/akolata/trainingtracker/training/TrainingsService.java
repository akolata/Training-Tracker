package pl.akolata.trainingtracker.training;

interface TrainingsService {
    Training createTraining(CreateTrainingCommand command);

    Training findTrainingById(Long id);

    TrainingSet addTrainingSetToTraining(CreateTrainingSetCommand command);
}
