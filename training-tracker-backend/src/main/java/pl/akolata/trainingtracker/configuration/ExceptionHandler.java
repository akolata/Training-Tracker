package pl.akolata.trainingtracker.configuration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.akolata.trainingtracker.shared.ApiResponse;
import pl.akolata.trainingtracker.shared.ValidationErrorsResponse;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
class ExceptionHandler extends ResponseEntityExceptionHandler {

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
}
