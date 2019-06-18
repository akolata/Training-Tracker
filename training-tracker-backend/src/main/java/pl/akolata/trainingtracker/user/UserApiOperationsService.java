package pl.akolata.trainingtracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.training.*;

@Service
class UserApiOperationsService {

    private final UserFacade userFacade;
    private final TrainingsFacade trainingsFacade;
    private final TrainingEntityMapper trainingMapper;
    private final TrainingSetEntityMapper trainingSetEntityMapper;

    @Autowired
    UserApiOperationsService(TrainingsFacade trainingsFacade, UserFacade userFacade, TrainingEntityMapper trainingMapper, TrainingSetEntityMapper trainingSetEntityMapper) {
        this.trainingsFacade = trainingsFacade;
        this.userFacade = userFacade;
        this.trainingMapper = trainingMapper;
        this.trainingSetEntityMapper = trainingSetEntityMapper;
    }

    TrainingApiDto addTrainingToUser(CreateTrainingCommand command, Long userId) {
        Training training = trainingsFacade.createTraining(command);
        userFacade.addTrainingToUser(training);
        return trainingMapper.toApiDto(training);
    }

    Page<TrainingApiDto> getUserTrainings(Long userId, Pageable pageable) {
        Page<Training> trainingsPage = trainingsFacade.findTraingsByUserId(userId, pageable);
        return trainingsPage.map(trainingMapper::toApiDto);
    }

    TrainingSetDto addTrainingSetToUserTraining(CreateTrainingSetCommand command) {
        TrainingSet trainingSet = trainingsFacade.addSetToTraining(command);
        return trainingSetEntityMapper.toDto(trainingSet);
    }
}
