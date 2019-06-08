package pl.akolata.trainingtracker.exercise;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.hateoas.ResourceSupport;

@Value
@EqualsAndHashCode(callSuper = false)
class ExerciseApiDto extends ResourceSupport {
    private final ExerciseDto exercise;
}
