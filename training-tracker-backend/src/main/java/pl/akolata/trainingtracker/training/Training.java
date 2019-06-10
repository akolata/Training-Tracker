package pl.akolata.trainingtracker.training;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.gym.Gym;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TRAINING")
@Setter
@ToString
public class Training extends BaseEntity {

    @Column(nullable = false)
    @Getter
    private LocalDate date;

    @Column
    @Getter
    private String additionalInfo;

    @Column
    @Getter
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "GYM_ID",
            foreignKey = @ForeignKey(name = "FK_TRAINING_GYM_ID")
    )
    @Getter
    private Gym gym;

    @OneToMany(
            mappedBy = "training",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private Set<TrainingSet> sets = new HashSet<>();

    public void addTrainingSet(TrainingSet trainingSet) {
        trainingSet.setTraining(this);
        sets.add(trainingSet);
    }

    public Set<TrainingSet> getSets() {
        return Collections.unmodifiableSet(sets);
    }
}
