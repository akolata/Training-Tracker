package pl.akolata.trainingtracker.training;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.akolata.trainingtracker.gym.GymDto;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TrainingDto extends BaseDto {
    private String name;
    private String additionalInfo;
    private LocalDate date;
    private GymDto gym;
    private Long userId;
    private List<TrainingSetDto> sets;
}
