package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.gym.GymFacade;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;

import java.util.Objects;

@Service
class TrainingsDatabaseService implements TrainingsService {

    private final GymFacade gymFacade;
    private final TrainingsRepository trainingsRepository;

    @Autowired
    TrainingsDatabaseService(GymFacade gymFacade, TrainingsRepository trainingsRepository) {
        this.gymFacade = gymFacade;
        this.trainingsRepository = trainingsRepository;
    }

    @Override
    public Training createTraining(CreateTrainingCommand command) {
        Objects.requireNonNull(command);

        if (gymIdIsNotValid(command.getGymId())) {
            throw new ResourceCreationFailureException("There is no gym with id " + command.getGymId());
        }

        Training training = trainingEntityFromCommand(command);
        return trainingsRepository.saveAndFlush(training);
    }

    private boolean gymIdIsNotValid(Long gymId) {
        if (gymId == null) {
            return false;
        }

        return gymFacade.getGymById(gymId) == null;
    }

    private Training trainingEntityFromCommand(CreateTrainingCommand command) {
        Training training = new Training();

        training.setName(command.getName());
        training.setAdditionalInfo(command.getAdditionalInfo());
        training.setDate(command.getDate());
        if (command.getGymId() != null) {
            training.setGym(gymFacade.getGymById(command.getGymId()));
        }

        return training;
    }
}
