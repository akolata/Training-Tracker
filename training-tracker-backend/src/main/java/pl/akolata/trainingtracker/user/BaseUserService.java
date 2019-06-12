package pl.akolata.trainingtracker.user;

interface BaseUserService<T> {
    T findUserById(Long id);
}
