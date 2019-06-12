package pl.akolata.trainingtracker.training;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
class CreateTrainingRequest {

    @NotNull
    private final LocalDate date;

    @NotNull
    private final Long userId;

    private final Long gymId;
    private final String additionalInfo;
    private final String name;
}
