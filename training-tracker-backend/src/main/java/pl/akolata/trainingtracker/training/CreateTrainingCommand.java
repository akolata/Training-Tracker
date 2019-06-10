package pl.akolata.trainingtracker.training;

import lombok.Value;

import java.time.LocalDate;

@Value
class CreateTrainingCommand {
    private final LocalDate date;
    private final Long gymId;
    private final String additionalInfo;
    private final String name;
}
