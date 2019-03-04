package pl.akolata.trainingtracker;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("pl.akolata.trainingtracker")
@IncludeTags(Tags.INTEGRATION)
public class IntegrationTestsSuite {
}
