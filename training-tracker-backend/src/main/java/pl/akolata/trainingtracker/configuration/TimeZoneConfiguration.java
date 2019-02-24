package pl.akolata.trainingtracker.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
@Slf4j
public class TimeZoneConfiguration {

    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    public TimeZoneConfiguration() {
        TimeZone.setDefault(TIMEZONE_UTC);
        log.debug("Default timezone set to {}", TIMEZONE_UTC.getDisplayName());
    }
}
