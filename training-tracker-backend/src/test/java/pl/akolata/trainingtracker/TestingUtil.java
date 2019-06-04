package pl.akolata.trainingtracker;

import javax.validation.ConstraintViolation;
import java.util.Optional;
import java.util.Set;

public class TestingUtil {

    public static <T> Optional<ConstraintViolation<T>> getConstraintViolationByPath(Set<ConstraintViolation<T>> violations, String path) {
        return violations
                .stream()
                .filter(v -> v.getPropertyPath().toString().equals(path))
                .findFirst();
    }
}
