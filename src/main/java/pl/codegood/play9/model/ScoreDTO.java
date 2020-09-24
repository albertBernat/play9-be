package pl.codegood.play9.model;

import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Builder
@Data
public class ScoreDTO {
    private String username;
    private long score;
    private String timestamp;

    public static ScoreDTO from(Score highScore) {
        return ScoreDTO.builder()
                .username(highScore.getUsername())
                .score(highScore.getScore())
                .timestamp(highScore.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")))
                .build();
    }
}
