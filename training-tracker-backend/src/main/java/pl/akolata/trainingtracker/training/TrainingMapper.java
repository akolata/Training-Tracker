package pl.akolata.trainingtracker.training;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface TrainingMapper {
    TrainingDto toTrainingDto(Training training);
}
