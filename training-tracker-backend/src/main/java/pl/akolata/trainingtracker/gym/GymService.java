package pl.akolata.trainingtracker.gym;

interface GymService {
    Gym createGym(CreateGymCommand createGymCommand) throws GymCreationFailureException;
}
