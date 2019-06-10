package pl.akolata.trainingtracker.exercise;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.akolata.trainingtracker.shared.dto.BaseApiDto;

@Value
@EqualsAndHashCode(callSuper = false)
class ExerciseApiDto extends BaseApiDto {
    private final ExerciseDto exercise;
}
