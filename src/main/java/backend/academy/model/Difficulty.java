package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;

@Getter
public enum Difficulty {
    @JsonProperty("easy")
    EASY(8),
    @JsonProperty("medium")
    MEDIUM(6),
    @JsonProperty("hard")
    HARD(4);

    private final int attempts;

    Difficulty(int attempts) {
        this.attempts = attempts;
    }

    static List<String> getAllDifficulties() {
        return Arrays.stream(Difficulty.values()).map(Enum::name).map(String::toLowerCase).toList();
    }

    static Difficulty getByName(String name) {
        return Difficulty.valueOf(name.toUpperCase());
    }

    static Difficulty getRandomDifficulty() {
        return Difficulty.values()[(int) (Math.random() * Difficulty.values().length)];
    }
}
