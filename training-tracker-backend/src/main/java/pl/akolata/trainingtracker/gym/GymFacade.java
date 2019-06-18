package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.shared.ValidationResult;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;

import java.util.Objects;

@Service
public class GymFacade {

    private final GymService gymService;
    private final GymValidationService validationService;

    @Autowired
    public GymFacade(GymService gymService, GymValidationService validationService) {
        this.gymService = gymService;
        this.validationService = validationService;
    }

    public Gym findGymById(Long id) {
        Objects.requireNonNull(id);
        return gymService.findGymById(id);
    }

    Gym createGym(CreateGymCommand command) {
        ValidationResult validationResult = validationService.validateCreateGymCommand(command);
        if (validationResult.notValid()) {
            throw new ResourceCreationFailureException(validationResult.getErrorMsg());
        }
        return gymService.createGym(command);
    }

    Page<Gym> findGyms(Pageable pageable) {
        Objects.requireNonNull(pageable);
        return gymService.findGyms(pageable);
    }
}
