package pl.akolata.trainingtracker.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
class CreateUserTrainingRequest {

    @NotNull
    private final LocalDate date;
    private final Long gymId;
    private final String additionalInfo;
    private final String name;
}
