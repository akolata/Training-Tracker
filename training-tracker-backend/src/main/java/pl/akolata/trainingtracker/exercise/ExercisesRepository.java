package pl.akolata.trainingtracker.exercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ExercisesRepository extends JpaRepository<Exercise, Long> {
    boolean existsByNameAndType(String name, ExerciseType type);
}
