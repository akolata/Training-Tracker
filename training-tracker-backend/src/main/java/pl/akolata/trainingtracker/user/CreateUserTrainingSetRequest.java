package pl.akolata.trainingtracker.user;

import lombok.Data;

import javax.validation.constraints.NotNull;

// TODO add at least one not null validator
@Data
class CreateUserTrainingSetRequest {
    @NotNull
    private Long exerciseId;
    private Integer reps;
    private Integer weightInKg;
    private Integer calories;
    private Integer durationInMinutes;
    private Double distanceInKm;
    private String additionalInfo;
}
