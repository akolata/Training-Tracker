package pl.akolata.trainingtracker.exercise;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ExerciseMapper {
    ExerciseDto fromEntity(Exercise exercise);
}
