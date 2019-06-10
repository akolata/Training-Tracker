package pl.akolata.trainingtracker.training;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

@Value
@EqualsAndHashCode(callSuper = false)
class TrainingApiDto extends ResourceSupport {
    private final TrainingDto training;
}
