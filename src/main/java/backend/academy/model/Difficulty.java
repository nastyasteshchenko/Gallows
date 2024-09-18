package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.List;

public enum Difficulty {
    @JsonProperty("easy")
    EASY,
    @JsonProperty("medium")
    MEDIUM,
    @JsonProperty("hard")
    HARD;

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
