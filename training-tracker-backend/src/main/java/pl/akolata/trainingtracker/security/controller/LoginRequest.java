package pl.akolata.trainingtracker.security.controller;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
class LoginRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;
}
