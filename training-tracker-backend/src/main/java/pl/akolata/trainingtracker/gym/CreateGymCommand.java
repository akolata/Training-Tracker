package pl.akolata.trainingtracker.gym;

import lombok.Value;

@Value
class CreateGymCommand {
    private final String name;
}
