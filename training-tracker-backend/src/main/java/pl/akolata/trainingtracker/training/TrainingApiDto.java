package pl.akolata.trainingtracker.training;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.akolata.trainingtracker.shared.dto.BaseApiDto;

@Value
@EqualsAndHashCode(callSuper = false)
public class TrainingApiDto extends BaseApiDto {
    private final TrainingDto training;
}
