package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.akolata.trainingtracker.shared.service.BaseEntityMapper;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class ExerciseEntityMapper implements BaseEntityMapper<Exercise, ExerciseDto, ExerciseApiDto> {

    private final ExerciseMapper exerciseMapper;

    @Autowired
    public ExerciseEntityMapper(ExerciseMapper exerciseMapper) {
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public ExerciseDto toDto(Exercise entity) {
        return exerciseMapper.toExerciseDto(entity);
    }

    @Override
    public ExerciseApiDto toApiDto(ExerciseDto dto) {
        ExerciseApiDto apiDto = new ExerciseApiDto(dto);
        apiDto.add(linkTo(methodOn(ExercisesController.class).getExercise(dto.getId())).withSelfRel());
        return apiDto;
    }
}
