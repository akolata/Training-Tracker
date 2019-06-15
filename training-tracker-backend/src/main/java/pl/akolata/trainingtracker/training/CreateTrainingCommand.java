package pl.akolata.trainingtracker.training;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateTrainingCommand {
    private final LocalDate date;
    private final Long gymId;
    private final Long userId;
    private final String additionalInfo;
    private final String name;
}
