package pl.akolata.trainingtracker.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseDto {
    protected Long id;
}
