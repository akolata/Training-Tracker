package pl.akolata.trainingtracker.training;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
class TrainingDto extends BaseDto {
    private Long id;
    private String name;
    private String additionalInfo;
    private LocalDate date;
    // TODO add gym
    // TODO add sets
}
