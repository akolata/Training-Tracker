package pl.akolata.trainingtracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag(Tags.INTEGRATION)
class TrainingTrackerApplicationTest extends IntegrationTestBase {

    @Test
    @DisplayName("Spring context should load")
    void contextLoads() {
    }
}
