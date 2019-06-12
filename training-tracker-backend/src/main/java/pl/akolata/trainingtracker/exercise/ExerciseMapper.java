package pl.akolata.trainingtracker.exercise;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ExerciseMapper {
    ExerciseDto toExerciseDto(Exercise exercise);
}
