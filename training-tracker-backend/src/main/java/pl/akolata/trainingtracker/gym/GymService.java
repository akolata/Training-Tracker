package pl.akolata.trainingtracker.gym;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface GymService extends BaseGymService<Gym> {
    Gym createGym(CreateGymCommand createGymCommand) throws GymCreationFailureException;

    Page<Gym> findGyms(Pageable pageable);

    Gym findGymById(Long id);
}
