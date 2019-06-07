package pl.akolata.trainingtracker.gym;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

interface GymService {
    Gym createGym(CreateGymCommand createGymCommand) throws GymCreationFailureException;

    Page<GymDto> findGyms(Pageable pageable);

    Optional<GymDto> findGym(Long id);
}
