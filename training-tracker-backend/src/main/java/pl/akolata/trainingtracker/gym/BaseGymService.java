package pl.akolata.trainingtracker.gym;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface BaseGymService<T> {
    T createGym(CreateGymCommand command);

    Page<T> findGyms(Pageable pageable);

    T findGymById(Long id);
}
