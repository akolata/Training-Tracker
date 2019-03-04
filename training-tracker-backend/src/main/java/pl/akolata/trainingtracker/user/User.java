package pl.akolata.trainingtracker.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.akolata.trainingtracker.shared.BaseEntity;

import javax.persistence.*;
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
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    private UserAccountDetails userAccountDetails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_users")),
            inverseJoinColumns = @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "FK_roles")),
            foreignKey = @ForeignKey(name = "FK_users_roles"),
            inverseForeignKey = @ForeignKey(name = "FK_roles_users"))
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userAccountDetails = new UserAccountDetails(false, false, false, true);
    }
}
