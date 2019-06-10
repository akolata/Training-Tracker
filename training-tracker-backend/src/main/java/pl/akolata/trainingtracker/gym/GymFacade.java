package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GymFacade {

    private final GymService gymService;

    @Autowired
    public GymFacade(GymService gymService) {
        this.gymService = gymService;
    }

    public Gym getGymById(Long id) {
        return gymService.findGymById(id);
    }
}
