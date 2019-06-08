package pl.akolata.trainingtracker.exercise;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
class CreateExerciseRequest {

    @NotNull
    @Length(min = 3, max = 255)
    private String name;

    @NotNull
    private ExerciseType type;
}
