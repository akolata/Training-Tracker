package pl.akolata.trainingtracker.exercise;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(
        name = "EXERCISE",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_EXERCISE_NAME", columnNames = "NAME")
        }
)
@Getter
@Setter
@ToString
public class Exercise extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ExerciseType type;
}
