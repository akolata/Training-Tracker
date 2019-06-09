package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
class GymDatabaseService implements GymService {

    private final GymRepository gymRepository;

    @Autowired
    GymDatabaseService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Transactional
    @Override
    public Gym createGym(CreateGymCommand command) throws GymCreationFailureException {
        Objects.requireNonNull(command);

        if (gymRepository.existsByName(command.getName())) {
            throw new GymCreationFailureException("Gym's name is not unique");
        }

        return gymRepository.saveAndFlush(command.toGym());
    }

    @Override
    public Page<Gym> findGyms(Pageable pageable) {
        Objects.requireNonNull(pageable);
        return gymRepository.findAll(pageable);
    }

    @Override
    public Gym findGymById(Long id) {
        Objects.requireNonNull(id);
        Optional<Gym> gym = gymRepository.findById(id);
        return gym.orElse(null);
    }
}
