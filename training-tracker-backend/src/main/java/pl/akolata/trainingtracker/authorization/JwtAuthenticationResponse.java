package pl.akolata.trainingtracker.authorization;

import lombok.Data;

@Data
class JwtAuthenticationResponse {

    private static final String TOKEN_TYPE = "Bearer";

    private String accessToken;

    JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
