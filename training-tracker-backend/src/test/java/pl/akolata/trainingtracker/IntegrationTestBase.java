package pl.akolata.trainingtracker;

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
abstract class IntegrationTestBase {
}
