package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.ValidationResult;

import java.util.Objects;

@Component
class GymValidationService {
    private static final String DUPLICATED_GYM_MSG = "Gym with name '%s' already exists";
    private final GymService gymService;

    @Autowired
    GymValidationService(GymService gymService) {
        this.gymService = gymService;
    }

    ValidationResult validateCreateGymCommand(CreateGymCommand command) {
        Objects.requireNonNull(command);

        if (gymService.existsByName(command.getName())) {
            return ValidationResult.invalid(
                    String.format(DUPLICATED_GYM_MSG, command.getName())
            );
        }
        return ValidationResult.valid();
    }
}
