package pl.akolata.trainingtracker.training;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TrainingSetMapper.class)
public interface TrainingMapper {

    @Mapping(target = "userId", source = "user.id")
    TrainingDto toTrainingDto(Training training);
}
