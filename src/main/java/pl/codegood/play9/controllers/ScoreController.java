package pl.codegood.play9.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.codegood.play9.entities.Score;
import pl.codegood.play9.services.ScoreService;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreService scoreService;

    @GetMapping("/{id}")
    public ResponseEntity<Score> getScore(@PathVariable(name = "id") Long id) {
        Score foundScore = scoreService.get(id);
        return ResponseEntity.ok(foundScore);
    }

    @PostMapping
    public ResponseEntity<Score> createScore(@RequestBody Input input) {
        Score scoreEntity = input.asScore();
        Score savedScore = scoreService.save(scoreEntity);
        return ResponseEntity.created(URI.create("/api/score/" + savedScore.getId())).build();
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
