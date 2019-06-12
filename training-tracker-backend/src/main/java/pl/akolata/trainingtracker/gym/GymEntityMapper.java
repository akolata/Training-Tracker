package pl.akolata.trainingtracker.gym;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.service.BaseEntityMapper;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class GymEntityMapper implements BaseEntityMapper<Gym, GymDto, GymApiDto> {

    private final GymMapper gymMapper;

    @Autowired
    public GymEntityMapper(GymMapper gymMapper) {
        this.gymMapper = gymMapper;
    }

    @Override
    public GymDto toDto(Gym gym) {
        return gymMapper.toGymDto(gym);
    }

    @Override
    public GymApiDto toApiDto(GymDto dto) {
        GymApiDto gymApiDto = new GymApiDto(dto);
        gymApiDto.add(linkTo(methodOn(GymController.class).getGym(dto.getId())).withSelfRel());
        return gymApiDto;
    }
}
