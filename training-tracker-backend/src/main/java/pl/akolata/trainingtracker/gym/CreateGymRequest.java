package pl.akolata.trainingtracker.gym;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
class CreateGymRequest {

    @Length(min = 5, max = 255)
    private String name;
}
