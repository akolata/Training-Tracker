package pl.akolata.trainingtracker.gym;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
class CreateGymRequest {

    /// TODO add messages from message bundle
    @NotNull
    @Length(min = 5, max = 255)
    private String name;
}
