package pl.akolata.trainingtracker.training;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.akolata.trainingtracker.exercise.ExerciseDto;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingSetDto extends BaseDto {

    private ExerciseDto exercise;
    private Long trainingId;
    private Integer reps;
    private Integer weight;
    private Integer calories;
    private Integer durationInMinutes;
    private Double distanceInKm;
    private String additionalInfo;
}
