package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.shared.exception.ResourceNotFoundException;

@Service
class GymApiHateoasService implements GymApiService {

    private final GymService gymService;
    private final GymEntityMapper gymMapper;

    @Autowired
    GymApiHateoasService(GymService gymService, GymEntityMapper gymMapper) {
        this.gymService = gymService;
        this.gymMapper = gymMapper;
    }

    @Override
    public GymApiDto createGym(CreateGymCommand command) {
        Gym gym = gymService.createGym(command);
        return gymMapper.toApiDto(gym);
    }

    @Override
    public Page<GymApiDto> findGyms(Pageable pageable) {
        return gymService.findGyms(pageable)
                .map(gymMapper::toApiDto);
    }

    @Override
    public GymApiDto findGymById(Long id) {
        Gym gym = gymService.findGymById(id);

        if (gym == null) {
            throw new ResourceNotFoundException("Gym with id " + id + " not found");
        }

        return gymMapper.toApiDto(gym);
    }
}
