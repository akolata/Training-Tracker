package pl.akolata.trainingtracker.gym;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface GymMapper {
    GymDto toGymDto(Gym gym);
}
