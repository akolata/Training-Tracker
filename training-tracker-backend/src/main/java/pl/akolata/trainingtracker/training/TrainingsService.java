package pl.akolata.trainingtracker.training;

interface TrainingsService {
    Training createTraining(CreateTrainingCommand command);

    Training findTrainingById(Long id);

    Training addTrainingSetToTraining(CreateTrainingSetCommand command);
}
