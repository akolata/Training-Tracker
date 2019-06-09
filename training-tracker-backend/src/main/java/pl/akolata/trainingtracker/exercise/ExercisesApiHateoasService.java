package pl.akolata.trainingtracker.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
class ExercisesApiHateoasService implements ExercisesApiService {

    private final ExercisesService exercisesService;
    private final ExerciseMapper exerciseMapper;

    @Autowired
    ExercisesApiHateoasService(ExercisesService exercisesService, ExerciseMapper exerciseMapper) {
        this.exercisesService = exercisesService;
        this.exerciseMapper = exerciseMapper;
    }

    @Override
    public ExerciseApiDto createExercise(CreateExerciseCommand command) throws ExerciseCreationFailureException {
        Exercise exercise = exercisesService.createExercise(command);
        return mapToApiDto(exercise);
    }

    @Override
    public Page<ExerciseApiDto> findExercises(Pageable pageable) {
        return exercisesService.findExercises(pageable)
                .map(this::mapToApiDto);
    }

    @Override
    public ExerciseApiDto findExerciseById(Long id) {
        Exercise exercise = exercisesService.findExerciseById(id);
        return mapToApiDto(exercise);
    }

    private ExerciseApiDto mapToApiDto(Exercise exercise) {
        ExerciseDto exerciseDto = exerciseMapper.fromEntity(exercise);
        return mapToApiDto(exerciseDto);
    }

    private ExerciseApiDto mapToApiDto(ExerciseDto exerciseDto) {
        ExerciseApiDto exerciseApiDto = new ExerciseApiDto(exerciseDto);
        exerciseApiDto.add(linkTo(methodOn(ExercisesController.class).getExercise(exerciseDto.getId())).withSelfRel());
        return exerciseApiDto;
    }
}
