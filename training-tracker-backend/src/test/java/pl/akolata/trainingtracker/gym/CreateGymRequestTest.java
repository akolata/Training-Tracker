package pl.akolata.trainingtracker.gym;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pl.akolata.trainingtracker.Tags;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.akolata.trainingtracker.TestingUtil.getConstraintViolationByPath;

@Tag(Tags.UNIT)
class CreateGymRequestTest {

    private static final String FIELD_NAME = "name";

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("should be invalid if gym's name it null")
    void validate_shouldBeInvalidForNullName() {
        CreateGymRequest createGymRequest = new CreateGymRequest();
        createGymRequest.setName(null);

        Set<ConstraintViolation<CreateGymRequest>> violations = validator.validate(createGymRequest);

        assertThat(violations, Matchers.not(Matchers.empty()));

        Optional<ConstraintViolation<CreateGymRequest>> nameViolationOpt = getConstraintViolationByPath(violations, FIELD_NAME);
        assertTrue(nameViolationOpt.isPresent());

        ConstraintViolation<CreateGymRequest> nameViolation = nameViolationOpt.get();
        assertEquals("must not be null", nameViolation.getMessage());
    }

    @Test
    @DisplayName("should be invalid if gym's name is an empty string")
    void validate_shouldBeInvalidForEmptyName() {
        CreateGymRequest createGymRequest = new CreateGymRequest();
        createGymRequest.setName("");

        Set<ConstraintViolation<CreateGymRequest>> violations = validator.validate(createGymRequest);

        assertThat(violations, Matchers.not(Matchers.empty()));

        Optional<ConstraintViolation<CreateGymRequest>> nameViolationOpt = getConstraintViolationByPath(violations, FIELD_NAME);
        assertTrue(nameViolationOpt.isPresent());

        ConstraintViolation<CreateGymRequest> nameViolation = nameViolationOpt.get();
        assertEquals("length must be between 5 and 255", nameViolation.getMessage());
    }

    @Test
    @DisplayName("should be invalid if gym's name is too short")
    void validate_shouldBeInvalidForTooShortName() {
        CreateGymRequest createGymRequest = new CreateGymRequest();
        createGymRequest.setName("c".repeat(4));

        Set<ConstraintViolation<CreateGymRequest>> violations = validator.validate(createGymRequest);

        assertThat(violations, Matchers.not(Matchers.empty()));

        Optional<ConstraintViolation<CreateGymRequest>> nameViolationOpt = getConstraintViolationByPath(violations, FIELD_NAME);
        assertTrue(nameViolationOpt.isPresent());

        ConstraintViolation<CreateGymRequest> nameViolation = nameViolationOpt.get();
        assertEquals("length must be between 5 and 255", nameViolation.getMessage());
    }

    @Test
    @DisplayName("should be invalid if gym's name is too long")
    void validate_shouldBeInvalidForTooLongName() {
        CreateGymRequest createGymRequest = new CreateGymRequest();
        createGymRequest.setName("c".repeat(256));

        Set<ConstraintViolation<CreateGymRequest>> violations = validator.validate(createGymRequest);

        assertThat(violations, Matchers.not(Matchers.empty()));

        Optional<ConstraintViolation<CreateGymRequest>> nameViolationOpt = getConstraintViolationByPath(violations, FIELD_NAME);
        assertTrue(nameViolationOpt.isPresent());

        ConstraintViolation<CreateGymRequest> nameViolation = nameViolationOpt.get();
        assertEquals("length must be between 5 and 255", nameViolation.getMessage());
    }
}