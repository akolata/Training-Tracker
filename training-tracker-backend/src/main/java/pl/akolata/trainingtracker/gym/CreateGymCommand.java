package pl.akolata.trainingtracker.gym;

import lombok.Value;

@Value
class CreateGymCommand {
    private final String name;

    Gym toGym() {
        Gym gym = new Gym();
        gym.setName(name);
        return gym;
    }
}
