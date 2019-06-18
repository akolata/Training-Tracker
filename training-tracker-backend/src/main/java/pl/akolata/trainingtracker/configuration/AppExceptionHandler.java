package pl.akolata.trainingtracker.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.ValidationErrorsResponse;
import pl.akolata.trainingtracker.shared.exception.ResourceCreationFailureException;
import pl.akolata.trainingtracker.shared.exception.ResourceNotFoundException;
import pl.akolata.trainingtracker.shared.exception.UserSignUpException;

import java.util.*;

@ControllerAdvice
@Slf4j
class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        Map<String, List<String>> errors = new LinkedHashMap<>();
        List<FieldError> allErrors = bindingResult.getFieldErrors();
        allErrors.forEach(e -> {
            List<String> fieldErrors = errors.getOrDefault(e.getField(), new LinkedList<>());
            fieldErrors.add(e.getDefaultMessage());
            errors.put(e.getField(), fieldErrors);
        });

        ValidationErrorsResponse errorsResponse = new ValidationErrorsResponse(errors);
        return new ResponseEntity<>(new ApiResponse<>(false, errorsResponse), headers, status);
    }

    @ExceptionHandler(value = UserSignUpException.class)
    ResponseEntity<ApiResponse<ValidationErrorsResponse>> handleSignUpFailure(UserSignUpException e) {
        log.debug("Exception {} handled", UserSignUpException.class);
        ValidationErrorsResponse errorsResponse = new ValidationErrorsResponse();
        List<String> errorsList = Collections.singletonList(e.getMessage());
        errorsResponse.getErrors().put(e.getField(), errorsList);
        return ResponseEntity
                .badRequest()
                .body(new ApiResponse<>(false, errorsResponse));
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    ResponseEntity handleResourceNotFound(ResourceNotFoundException e) {
        log.debug("Exception {} handled, reason: {}", ResourceNotFoundException.class.getSimpleName(), e.getMessage());
        return ResponseEntity
                .notFound()
                .build();
    }

    @ExceptionHandler({ResourceCreationFailureException.class})
    ResponseEntity<ApiResponse<String>> handleResourceCreationFailure(ResourceCreationFailureException e) {
        log.debug("Exception {} handled, reason: {}", ResourceCreationFailureException.class.getSimpleName(), e.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.failure(e.getMessage()));
    }
}
