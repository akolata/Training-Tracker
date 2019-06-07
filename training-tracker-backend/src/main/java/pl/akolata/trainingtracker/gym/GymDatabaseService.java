package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
class GymDatabaseService implements GymService {

    private final GymRepository gymRepository;
    private final GymMapper gymMapper;

    @Autowired
    GymDatabaseService(GymRepository gymRepository, GymMapper gymMapper) {
        this.gymRepository = gymRepository;
        this.gymMapper = gymMapper;
    }

    @Transactional
    @Override
    public Gym createGym(CreateGymCommand createGymCommand) throws GymCreationFailureException {
        if (gymRepository.existsByName(createGymCommand.getName())) {
            throw new GymCreationFailureException("Gym's name is not unique");
        }

        Gym gym = new Gym();
        gym.setName(createGymCommand.getName());
        return gymRepository.save(gym);
    }

    @Override
    public Page<GymDto> findGyms(Pageable pageable) {
        Page<Gym> gymsPage = gymRepository.findAll(pageable);
        return gymsPage.map(gymMapper::fromEntity);
    }

    @Override
    public Optional<GymDto> findGym(Long id) {
        Optional<Gym> gym = gymRepository.findById(id);
        if (gym.isPresent()) {
            return gym.map(gymMapper::fromEntity);
        }
        return Optional.empty();
    }
}
