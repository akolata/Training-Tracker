package pl.akolata.trainingtracker.domain.user.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDetails {

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean accountExpired;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean accountLocked;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean credentialsExpired;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean enabled;
}
