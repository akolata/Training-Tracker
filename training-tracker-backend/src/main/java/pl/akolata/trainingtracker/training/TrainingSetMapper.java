package pl.akolata.trainingtracker.training;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface TrainingSetMapper {

    @Mapping(target = "trainingId", source = "training.id")
    TrainingSetDto toTrainingSetDto(TrainingSet trainingSet);
}
