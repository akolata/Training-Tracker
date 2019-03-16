package pl.akolata.trainingtracker.shared;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private Boolean success;
    private T data;
}
