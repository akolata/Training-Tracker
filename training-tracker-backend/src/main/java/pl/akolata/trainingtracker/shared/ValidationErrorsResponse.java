package pl.akolata.trainingtracker.shared;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ValidationErrorsResponse {

    private Map<String, List<String>> errors = new LinkedHashMap<>();

    public ValidationErrorsResponse() {
    }

    public ValidationErrorsResponse(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }
}
