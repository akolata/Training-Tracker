package pl.akolata.trainingtracker.timezone;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
@Log4j2
public class TimeZoneConfiguration {

    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone("UTC");

    public TimeZoneConfiguration() {
        TimeZone.setDefault(TIMEZONE_UTC);
        log.debug("Default timezone set to {}", TIMEZONE_UTC.getDisplayName());
    }
}
