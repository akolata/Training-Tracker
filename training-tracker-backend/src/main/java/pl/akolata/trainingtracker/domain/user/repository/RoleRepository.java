package pl.akolata.trainingtracker.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akolata.trainingtracker.domain.user.entity.Role;
import pl.akolata.trainingtracker.domain.user.model.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
