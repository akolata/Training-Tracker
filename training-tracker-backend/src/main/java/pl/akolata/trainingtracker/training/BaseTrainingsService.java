package pl.akolata.trainingtracker.training;

interface BaseTrainingsService<T> {
    T createTraining(CreateTrainingCommand command);
}
