package pl.akolata.trainingtracker.domain.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.domain.user.model.RoleName;
import pl.akolata.trainingtracker.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }
}
