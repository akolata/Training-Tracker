package pl.akolata.trainingtracker.exercise;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.BaseApiController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
class ExercisesController extends BaseApiController {
    private static final String EXERCISE_URL = "/exercises/{exerciseId}";
    private static final String EXERCISES_URL = "/exercises";

    private final ExercisesApiService apiService;

    @Autowired
    ExercisesController(ExercisesApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping(
            path = EXERCISES_URL,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<ExerciseDto>> addExercise(@Valid @RequestBody CreateExerciseRequest request) {
        ExerciseDto exercise = apiService.createExercise(request);
        URI location = getResourceLocation(EXERCISE_URL, exercise.getId());
        log.debug("Exercise with name {} and type {} created", exercise.getName(), exercise.getType());

        return ResponseEntity
                .created(location)
                .body(ApiResponse.success(exercise));
    }

    @GetMapping(
            path = EXERCISES_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<Page<ExerciseDto>>> getExercises(Pageable pageable) {
        Page<ExerciseDto> exercises = apiService.findExercises(pageable != null ? pageable : getDefaultPageable());
        return ResponseEntity
                .ok(ApiResponse.success(exercises));
    }

    @GetMapping(
            path = EXERCISE_URL,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    ResponseEntity<ApiResponse<ExerciseDto>> getExercise(@PathVariable Long exerciseId) {
        ExerciseDto exercise = apiService.findExerciseById(exerciseId);
        return ResponseEntity
                .ok(ApiResponse.success(exercise));
    }
}
