package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
class GymApiService {

    private final GymMapper gymMapper;
    private final GymFacade gymFacade;
    private final GymRequestToCommandConverter toCommandConverter;

    @Autowired
    GymApiService(GymMapper gymMapper, GymFacade gymFacade, GymRequestToCommandConverter toCommandConverter) {
        this.gymMapper = gymMapper;
        this.gymFacade = gymFacade;
        this.toCommandConverter = toCommandConverter;
    }

    GymDto createGym(CreateGymRequest request) {
        CreateGymCommand command = toCommandConverter.createGymRequestToCommand(request);
        Gym gym = gymFacade.createGym(command);
        return gymMapper.toGymDto(gym);
    }

    Page<GymDto> findGyms(Pageable pageable) {
        return gymFacade
                .findGyms(pageable)
                .map(gymMapper::toGymDto);
    }

    GymDto findGymById(Long id) {
        Gym gym = gymFacade.findGymById(id);
        return gymMapper.toGymDto(gym);
    }
}
