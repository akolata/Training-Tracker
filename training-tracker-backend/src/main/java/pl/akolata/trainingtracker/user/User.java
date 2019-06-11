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
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_USERNAME", columnNames = "username"),
                @UniqueConstraint(name = "UK_EMAIL", columnNames = "email")
        },
        indexes = {
                @Index(name = "IX_EMAIL", columnList = "email"),
                @Index(name = "IX_USERNAME", columnList = "username")
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
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_users")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_roles")),
            foreignKey = @ForeignKey(name = "FK_users_roles"),
            inverseForeignKey = @ForeignKey(name = "FK_roles_users"))
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
