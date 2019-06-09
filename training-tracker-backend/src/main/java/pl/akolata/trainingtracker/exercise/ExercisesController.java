package pl.akolata.trainingtracker.exercise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;

@RestController
class ExercisesController extends BaseApiController {
    private static final String EXERCISES_URL = "/exercises";

    private final ExercisesApiService exercisesService;

    @Autowired
    ExercisesController(ExercisesApiService exercisesService) {
        this.exercisesService = exercisesService;
    }

    @PostMapping(
            path = EXERCISES_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<ExerciseApiDto>> addExercise(@Valid @RequestBody CreateExerciseRequest request) throws ExerciseCreationFailureException {
        CreateExerciseCommand command = new CreateExerciseCommand(request.getName(), request.getType());
        ExerciseApiDto exercise = exercisesService.createExercise(command);
        return ResponseEntity.ok(ApiResponse.success(exercise));
    }

    @GetMapping(
            path = EXERCISES_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<Page<ExerciseApiDto>>> getExercises(Pageable pageable) {
        Page<ExerciseApiDto> exercises = exercisesService.findExercises(pageable != null ? pageable : getDefaultPageable());
        return ResponseEntity.ok(ApiResponse.success(exercises));
    }

    @GetMapping(
            path = EXERCISES_URL + "/{id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<ExerciseApiDto>> getExercise(@PathVariable Long id) {
        ExerciseApiDto exercise = exercisesService.findExerciseById(id);
        if (exercise != null) {
            return ResponseEntity.ok(ApiResponse.success(exercise));
        }
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ExerciseCreationFailureException.class)
    ResponseEntity<ApiResponse<String>> handleCreationFailure(ExerciseCreationFailureException e) {
        return ResponseEntity.badRequest().body(ApiResponse.failure(e.getMessage()));
    }
}
