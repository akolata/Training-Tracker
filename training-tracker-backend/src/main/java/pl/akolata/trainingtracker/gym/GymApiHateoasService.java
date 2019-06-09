package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.akolata.trainingtracker.shared.exception.ResourceNotFoundException;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
class GymApiHateoasService implements GymApiService {

    private final GymService gymService;
    private final GymMapper gymMapper;

    @Autowired
    GymApiHateoasService(GymService gymService, GymMapper gymMapper) {
        this.gymService = gymService;
        this.gymMapper = gymMapper;
    }

    @Override
    public GymApiDto createGym(CreateGymCommand command) {
        Gym gym = gymService.createGym(command);
        return mapToApiDto(gym);
    }

    @Override
    public Page<GymApiDto> findGyms(Pageable pageable) {
        return gymService.findGyms(pageable)
                .map(this::mapToApiDto);
    }

    @Override
    public GymApiDto findGymById(Long id) {
        Gym gym = gymService.findGymById(id);

        if (gym == null) {
            throw new ResourceNotFoundException("Gym with id " + id + " not found");
        }

        return mapToApiDto(gym);
    }

    private GymApiDto mapToApiDto(Gym gym) {
        GymDto gymDto = gymMapper.fromEntity(gym);
        return mapToApiDto(gymDto);
    }

    private GymApiDto mapToApiDto(GymDto gymDto) {
        GymApiDto gymApiDto = new GymApiDto(gymDto);
        gymApiDto.add(linkTo(methodOn(GymController.class).getGym(gymDto.getId())).withSelfRel());
        return gymApiDto;
    }
}
