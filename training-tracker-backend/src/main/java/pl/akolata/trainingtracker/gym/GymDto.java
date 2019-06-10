package pl.akolata.trainingtracker.gym;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.akolata.trainingtracker.shared.dto.BaseDto;

@Data
@EqualsAndHashCode(callSuper = true)
class GymDto extends BaseDto {
    private String name;
}
