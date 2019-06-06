package pl.akolata.trainingtracker.gym;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface GymService {
    Gym createGym(CreateGymCommand createGymCommand) throws GymCreationFailureException;

    Page<GymDto> findGyms(Pageable pageable);
}
