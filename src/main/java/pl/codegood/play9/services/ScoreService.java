package pl.codegood.play9.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.codegood.play9.entities.Score;
import pl.codegood.play9.repositories.ScoreRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ScoreService {

    private static final int PAGE_SIZE = 10;

    private final ScoreRepository scoreRepository;

    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    public Score get(Long id) {
        return scoreRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public List<Score> getBestScores(Integer page) {
        return scoreRepository.findAllScores(PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC,"score")));
    }
}
