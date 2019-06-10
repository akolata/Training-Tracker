package pl.akolata.trainingtracker.training;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrainingsRepository extends JpaRepository<Training, Long> {
}
