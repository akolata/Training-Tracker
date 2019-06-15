package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO implement mapper interface after API for training sets will be added
@Component
public class TrainingSetEntityMapper {

    private final TrainingSetMapper trainingSetMapper;

    @Autowired
    public TrainingSetEntityMapper(TrainingSetMapper trainingSetMapper) {
        this.trainingSetMapper = trainingSetMapper;
    }

    public TrainingSetDto toDto(TrainingSet trainingSet) {
        return trainingSetMapper.toTrainingSetDto(trainingSet);
    }
}
