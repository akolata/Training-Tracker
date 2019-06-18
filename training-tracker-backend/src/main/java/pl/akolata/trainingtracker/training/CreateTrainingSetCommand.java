package pl.akolata.trainingtracker.training;

import lombok.Value;

@Value
public class CreateTrainingSetCommand {
    private Long exerciseId;
    private Long trainingId;
    private Integer reps;
    private Integer weight;
    private Integer calories;
    private Integer durationInMinutes;
    private Double distanceInKm;
    private String additionalInfo;
}
