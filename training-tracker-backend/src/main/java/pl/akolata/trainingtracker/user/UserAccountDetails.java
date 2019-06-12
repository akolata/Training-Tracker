package pl.akolata.trainingtracker.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountDetails implements Serializable {

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean accountExpired;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean accountLocked;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean credentialsExpired;

    @Column(nullable = false, columnDefinition = "BIT(1)")
    private Boolean enabled;
}
