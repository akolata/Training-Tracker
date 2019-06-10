package pl.akolata.trainingtracker.gym;

import lombok.EqualsAndHashCode;
import lombok.Value;
import pl.akolata.trainingtracker.shared.dto.BaseApiDto;

@Value
@EqualsAndHashCode(callSuper = false)
class GymApiDto extends BaseApiDto {
    private final GymDto gym;
}
