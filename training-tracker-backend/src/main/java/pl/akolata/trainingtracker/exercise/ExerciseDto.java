package pl.akolata.trainingtracker.exercise;

import lombok.Data;

@Data
class ExerciseDto {
    private Long id;
    private String name;
    private ExerciseType type;
}
