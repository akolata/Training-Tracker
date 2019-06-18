package pl.akolata.trainingtracker.authorization;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
class SignInRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
