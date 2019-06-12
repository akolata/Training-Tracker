package pl.akolata.trainingtracker.training;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.akolata.trainingtracker.gym.GymDto;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
class TrainingDto extends BaseDto {
    private String name;
    private String additionalInfo;
    private LocalDate date;
    private GymDto gym;
    private Long userId;
    // TODO add sets
}
