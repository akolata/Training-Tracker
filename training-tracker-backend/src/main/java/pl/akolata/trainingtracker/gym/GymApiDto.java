package pl.akolata.trainingtracker.gym;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

@Value
@EqualsAndHashCode(callSuper = false)
class GymApiDto extends ResourceSupport {
    private final GymDto gymDto;
}
