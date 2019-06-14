package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.trainingtracker.gym.GymFacade;
import pl.akolata.trainingtracker.user.UserFacade;

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
        Training training = trainingEntityFromCommand(command);
        return trainingsRepository.saveAndFlush(training);
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
