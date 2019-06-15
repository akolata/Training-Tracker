package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akolata.trainingtracker.exercise.Exercise;
import pl.akolata.trainingtracker.exercise.ExerciseFacade;
import pl.akolata.trainingtracker.gym.GymFacade;
import pl.akolata.trainingtracker.user.UserFacade;

@Service
class TrainingsDatabaseService implements TrainingsService {

    private final GymFacade gymFacade;
    private final UserFacade userFacade;
    private final ExerciseFacade exerciseFacade;
    private final TrainingsRepository trainingsRepository;

    @Autowired
    TrainingsDatabaseService(
            GymFacade gymFacade,
            UserFacade userFacade,
            ExerciseFacade exerciseFacade,
            TrainingsRepository trainingsRepository) {
        this.gymFacade = gymFacade;
        this.userFacade = userFacade;
        this.exerciseFacade = exerciseFacade;
        this.trainingsRepository = trainingsRepository;
    }

    @Transactional
    @Override
    public Training createTraining(CreateTrainingCommand command) {
        Training training = trainingEntityFromCommand(command);
        return trainingsRepository.saveAndFlush(training);
    }

    @Override
    public Training findTrainingById(Long id) {
        return trainingsRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Training addTrainingSetToTraining(CreateTrainingSetCommand command) {
        Training training = findTrainingById(command.getTrainingId());
        TrainingSet trainingSet = trainingSetEntityFromCommand(command);
        training.addTrainingSet(trainingSet);

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

    private TrainingSet trainingSetEntityFromCommand(CreateTrainingSetCommand command) {
        TrainingSet trainingSet = new TrainingSet();
        Exercise exercise = exerciseFacade.findExerciseById(command.getExerciseId());

        trainingSet.setExercise(exercise);
        trainingSet.setReps(command.getReps());
        trainingSet.setWeight(command.getWeight());
        trainingSet.setCalories(command.getCalories());
        trainingSet.setDurationInMinutes(command.getDurationInMinutes());
        trainingSet.setDistanceInKm(command.getDistanceInKm());
        trainingSet.setAdditionalInfo(command.getAdditionalInfo());

        return trainingSet;
    }
}
