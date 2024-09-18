package backend.academy.model.listener;

import java.util.List;

public interface ChooseDifficultyListener {

    void onChooseDifficulty(List<String> possibleDifficulties);
}
