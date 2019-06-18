package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class GymDatabaseService implements GymService {

    private final GymRepository gymRepository;

    @Autowired
    GymDatabaseService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    @Transactional
    @Override
    public Gym createGym(CreateGymCommand command) {
        return gymRepository.saveAndFlush(command.toGym());
    }

    @Override
    public Page<Gym> findGyms(Pageable pageable) {
        return gymRepository.findAll(pageable);
    }

    @Override
    public Gym findGymById(Long id) {
        return gymRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public boolean existsByName(String name) {
        return gymRepository.existsByName(name);
    }
}
