package pl.akolata.trainingtracker.training;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Value
class CreateTrainingRequest {

    @NotNull
    private final LocalDate date;
    private final Long gymId;
    private final String additionalInfo;
    private final String name;
}
