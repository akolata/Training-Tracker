package pl.akolata.trainingtracker.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import pl.akolata.trainingtracker.BaseJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoleRepositoryIntegrationTest extends BaseJpaTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @DisplayName("should not allow to create role with null name")
    void save_whenRoleNameIsNull_thenThrowException() {
        // given
        Role role = new Role(null);

        // when
        assertThrows(DataIntegrityViolationException.class, () -> roleRepository.saveAndFlush(role));
    }

    @Test
    @DisplayName("should not allow to create role with existing name")
    void save_whenRoleNameIsNotUnique_thenThrowException() {
        // given
        Role role = new Role(RoleName.ROLE_USER);
        roleRepository.saveAndFlush(role);

        // when
        assertThrows(DataIntegrityViolationException.class, () -> roleRepository.saveAndFlush(new Role(role.getName())));
    }

    @Test
    @DisplayName("created role should has fields from base entity")
    void save_whenRoleIsCreated_fieldsFromBaseEntityAreNotNull() {
        // given
        Role role = new Role(RoleName.ROLE_USER);

        // when
        role = roleRepository.saveAndFlush(role);

        // then
        assertNotNull(role.getId());
        assertNotNull(role.getUuid());
        assertNotNull(role.getUpdatedAt());
        assertNotNull(role.getCreatedAt());
        assertNotNull(role.getVersion());
    }

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }
}