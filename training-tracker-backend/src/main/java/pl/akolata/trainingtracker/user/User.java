package pl.akolata.trainingtracker.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.shared.BaseEntity;
import pl.akolata.trainingtracker.training.Training;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "USER",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_USER_USERNAME", columnNames = "USERNAME"),
                @UniqueConstraint(name = "UK_USER_EMAIL", columnNames = "EMAIL")
        }
)
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false)
    @Getter
    private String firstName;

    @Column(nullable = false)
    @Getter
    private String lastName;

    @Column(nullable = false, unique = true)
    @Getter
    private String username;

    @Column(nullable = false, unique = true)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Embedded
    @Getter
    private UserAccountDetails userAccountDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"),
            foreignKey = @ForeignKey(name = "FK_USER_ROLE"),
            inverseForeignKey = @ForeignKey(name = "FK_ROLE_USER"))
    @Getter
    private Set<Role> roles = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private Set<Training> trainings;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userAccountDetails = new UserAccountDetails(false, false, false, true);
    }

    public Set<Training> getTrainings() {
        return Collections.unmodifiableSet(trainings);
    }

    public void addTraining(Training training) {
        training.setUser(this);
        trainings.add(training);
    }
}
