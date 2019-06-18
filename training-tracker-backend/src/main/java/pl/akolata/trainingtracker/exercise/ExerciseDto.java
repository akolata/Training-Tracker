package pl.akolata.trainingtracker.exercise;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExerciseDto extends BaseDto {
    private String name;
    private ExerciseType type;
}
