package pl.codegood.play9.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.codegood.play9.entities.Score;
import pl.codegood.play9.repositories.ScoreRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    public Score get(Long id) {
        return scoreRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
