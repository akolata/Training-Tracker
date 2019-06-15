package pl.akolata.trainingtracker.training;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface TrainingsService {
    Training createTraining(CreateTrainingCommand command);

    Training findTrainingById(Long id);

    TrainingSet addTrainingSetToTraining(CreateTrainingSetCommand command);

    Page<Training> findTrainingsByUserId(Long userId, Pageable pageable);
}
