package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
class GymDatabaseService implements GymService {

    private final GymRepository gymRepository;

    @Autowired
    GymDatabaseService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
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
}
