package pl.codegood.play9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.codegood.play9.entities.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
