package pl.akolata.trainingtracker.training;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
class CreateTrainingRequest {

    @NotNull
    private LocalDate date;

    private Long gymId;

    private String additionalInfo;

    private String name;
}
