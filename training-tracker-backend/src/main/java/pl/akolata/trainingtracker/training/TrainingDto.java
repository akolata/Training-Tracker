package pl.akolata.trainingtracker.training;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
class TrainingDto {
    private Long id;
    private String name;
    private String additionalInfo;
    private LocalDate date;
    // TODO add gym
    // TODO add sets
}
