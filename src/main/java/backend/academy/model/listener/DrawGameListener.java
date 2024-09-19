package backend.academy.model.listener;

import java.util.List;

public interface DrawGameListener {

    void onDrawGame(List<Character> wordLetters, int mistakes, int attempts, String theme);
}
