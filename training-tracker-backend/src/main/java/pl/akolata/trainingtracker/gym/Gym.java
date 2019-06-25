package pl.akolata.trainingtracker.gym;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(
        name = "TT_GYM",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_GYM_NAME", columnNames = "NAME")
        }
)
@Getter
@Setter
@ToString(callSuper = true)
public class Gym extends BaseEntity {

    @Column(nullable = false)
    private String name;
}
