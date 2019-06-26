package pl.akolata.trainingtracker.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(
        name = "ROLE",
        uniqueConstraints = {@UniqueConstraint(name = "UK_NAME", columnNames = "name")}
)
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    Role(RoleName name) {
        this.name = name;
    }
}
