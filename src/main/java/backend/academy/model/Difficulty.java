package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Difficulty {

    @JsonProperty("easy")
    EASY(8),
    @JsonProperty("medium")
    MEDIUM(6),
    @JsonProperty("hard")
    HARD(4);

    private static final SecureRandom RANDOM = new SecureRandom();
    private final int attempts;

    Difficulty(int attempts) {
        this.attempts = attempts;
    }

    public static List<String> getAllDifficulties() {
        return Arrays.stream(Difficulty.values())
            .map(difficulty -> difficulty.name().toLowerCase())
            .toList();
    }

    public static Difficulty getByName(String name) {
        return Difficulty.valueOf(name.toUpperCase());
    }

    public static Difficulty getRandomDifficulty() {
        return Difficulty.values()[RANDOM.nextInt(Difficulty.values().length)];
    }
}
