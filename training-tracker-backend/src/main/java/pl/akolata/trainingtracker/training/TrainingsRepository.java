package pl.akolata.trainingtracker.training;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingsRepository extends JpaRepository<Training, Long> {
    Page<Training> findAllByUserId(Long userId, Pageable pageable);
}
