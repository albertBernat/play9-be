package pl.codegood.play9.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.codegood.play9.model.Score;
import pl.codegood.play9.model.ScoreDTO;
import pl.codegood.play9.services.ScoreService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scores")
@CrossOrigin
public class ScoreController {

    private static final int FIRST_PAGE = 0;

    private final ScoreService scoreService;

    @GetMapping("/best")
    public ResponseEntity<List<ScoreDTO>> getBestScores(@RequestParam(required = false) Integer page) {
        int pageNumber = page != null ? page : FIRST_PAGE;
        List<Score> bestScores = scoreService.getBestScores(pageNumber);
        List<ScoreDTO> scoreDTOS = bestScores.stream()
                .map(ScoreDTO::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(scoreDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScoreDTO> getScore(@PathVariable(name = "id") Long id) {
        Score foundScore = scoreService.get(id);
        return ResponseEntity.ok(ScoreDTO.from(foundScore));
    }

    @PostMapping
    public ResponseEntity<?> createScore(@RequestBody Input input) {
        Score scoreEntity = input.asScore();
        Score savedScore = scoreService.save(scoreEntity);
        return ResponseEntity.created(URI.create("/api/scores/" + savedScore.getId())).build();
    }

    @Data
    private static class Input {
        private String username;
        private long score;

        public Score asScore() {
            return Score.builder()
                    .username(username)
                    .score(score)
                    .timestamp(LocalDateTime.now())
                    .build();
        }
    }
}
