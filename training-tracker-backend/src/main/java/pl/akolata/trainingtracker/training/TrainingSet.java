package pl.akolata.trainingtracker.training;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.exercise.Exercise;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "TT_TRAINING_SET")
@Getter
@Setter
@ToString
public class TrainingSet extends BaseEntity {

    @ManyToOne
    @JoinColumn(
            name = "EXERCISE_ID",
            foreignKey = @ForeignKey(name = "FK_TRAINING_SET_EXERCISE_ID")
    )
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(
            name = "TRAINING_ID",
            foreignKey = @ForeignKey(name = "FK_TRAINING_SET_TRAINING_ID")
    )
    @ToString.Exclude
    private Training training;

    @Column
    private Integer reps;

    @Column
    private Integer weight;

    @Column
    private Integer calories;

    @Column
    private Integer durationInMinutes;

    @Column(scale = 2)
    private Double distanceInKm;

    @Column
    private String additionalInfo;
}
