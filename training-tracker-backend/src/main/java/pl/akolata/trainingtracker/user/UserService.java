package pl.akolata.trainingtracker.user;

import pl.akolata.trainingtracker.training.Training;

interface UserService {
    User findUserById(Long id);

    User addTrainingToUser(Training training);
}
