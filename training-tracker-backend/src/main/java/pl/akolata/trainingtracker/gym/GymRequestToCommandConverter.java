package pl.akolata.trainingtracker.gym;

import org.springframework.stereotype.Component;

@Component
class GymRequestToCommandConverter {

    CreateGymCommand createGymRequestToCommand(CreateGymRequest request) {
        return new CreateGymCommand(request.getName());
    }
}
