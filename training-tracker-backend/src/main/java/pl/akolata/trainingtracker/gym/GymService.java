package pl.akolata.trainingtracker.gym;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface GymService {
    Gym createGym(CreateGymCommand createGymCommand);

    Page<Gym> findGyms(Pageable pageable);

    Gym findGymById(Long id);

    boolean existsByName(String name);
}
