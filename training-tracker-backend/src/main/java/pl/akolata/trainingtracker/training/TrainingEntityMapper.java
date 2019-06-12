package pl.akolata.trainingtracker.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.service.BaseEntityMapper;

@Component
class TrainingEntityMapper implements BaseEntityMapper<Training, TrainingDto, TrainingApiDto> {

    private final TrainingMapper trainingMapper;

    @Autowired
    TrainingEntityMapper(TrainingMapper trainingMapper) {
        this.trainingMapper = trainingMapper;
    }

    @Override
    public TrainingDto toDto(Training training) {
        return trainingMapper.toTrainingDto(training);
    }

    @Override
    public TrainingApiDto toApiDto(TrainingDto dto) {
        return new TrainingApiDto(dto);
    }
}
