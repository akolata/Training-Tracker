package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.trainingtracker.gym.GymFacade;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;
import pl.akolata.trainingtracker.user.UserFacade;

import java.util.Objects;

@Service
class TrainingsDatabaseService implements TrainingsService {

    private final GymFacade gymFacade;
    private final UserFacade userFacade;
    private final TrainingsRepository trainingsRepository;

    @Autowired
    TrainingsDatabaseService(GymFacade gymFacade, UserFacade userFacade, TrainingsRepository trainingsRepository) {
        this.gymFacade = gymFacade;
        this.userFacade = userFacade;
        this.trainingsRepository = trainingsRepository;
    }

    @Transactional
    @Override
    public Training createTraining(CreateTrainingCommand command) {
        Objects.requireNonNull(command);
        validateUserAndGymId(command.getGymId(), command.getUserId());

        Training training = trainingEntityFromCommand(command);
        return trainingsRepository.saveAndFlush(training);
    }

    private void validateUserAndGymId(Long gymId, Long userId) {
        if (gymId != null && gymFacade.getGymById(gymId) == null) {
            throw new ResourceCreationFailureException("There is no gym with id " + gymId);
        }

        if (userId == null) {
            throw new ResourceCreationFailureException("User id must not be null");
        }

        if (userFacade.getUserById(userId) == null) {
            throw new ResourceCreationFailureException("There is no user with id " + userId);
        }
    }

    private Training trainingEntityFromCommand(CreateTrainingCommand command) {
        Training training = new Training();

        training.setName(command.getName());
        training.setAdditionalInfo(command.getAdditionalInfo());
        training.setDate(command.getDate());
        if (command.getGymId() != null) {
            training.setGym(gymFacade.getGymById(command.getGymId()));
        }
        training.setUser(userFacade.getUserById(command.getUserId()));

        return training;
    }
}
