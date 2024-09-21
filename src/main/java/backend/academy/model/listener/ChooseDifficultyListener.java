package backend.academy.model.listener;

import java.util.List;

public interface ChooseDifficultyListener {
    String onChooseDifficulty(List<String> possibleDifficulties);
}
