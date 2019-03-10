package pl.akolata.trainingtracker

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import pl.akolata.trainingtracker.user.Role
import pl.akolata.trainingtracker.user.RoleName
import pl.akolata.trainingtracker.user.RoleRepository
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class BaseSpecification extends Specification {

    @Autowired
    protected MockMvc mvc

    @Autowired
    protected RoleRepository roleRepository

    protected static String toJson(Object o) {
        ObjectMapper om = new ObjectMapper()
        om.writeValueAsString(o)
    }

    def setup() {
        def role = new Role()
        role.setName(RoleName.ROLE_USER)
        roleRepository.saveAndFlush(role)
    }
}
