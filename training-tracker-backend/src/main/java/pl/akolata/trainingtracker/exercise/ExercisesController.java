package pl.akolata.trainingtracker.exercise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;

@RestController
class ExercisesController extends BaseApiController {
    private static final String EXERCISES_URL = "/exercises";

    private final ExercisesService exercisesService;

    @Autowired
    ExercisesController(ExercisesService exercisesService) {
        this.exercisesService = exercisesService;
    }

    @PostMapping(
            path = EXERCISES_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<ExerciseApiDto>> addExercise(@Valid @RequestBody CreateExerciseRequest request) throws ExerciseCreationFailureException {
        CreateExerciseCommand command = new CreateExerciseCommand(request.getName(), request.getType());
        ExerciseDto exercise = exercisesService.createExercise(command);
        return ResponseEntity.ok(ApiResponse.success(new ExerciseApiDto(exercise)));
    }

    @ExceptionHandler(ExerciseCreationFailureException.class)
    ResponseEntity<ApiResponse<String>> handleCreationFailure(ExerciseCreationFailureException e) {
        return ResponseEntity.badRequest().body(ApiResponse.failure(e.getMessage()));
    }
}
