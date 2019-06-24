package pl.akolata.trainingtracker.gym;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(
        name = "gym",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_GYM_NAME", columnNames = "name")
        },
        indexes = {
                @Index(name = "IX_GYM_NAME", columnList = "name")
        }
)
@Getter
@Setter
@ToString(callSuper = true)
public class Gym extends BaseEntity {

    @Column(nullable = false)
    private String name;
}
