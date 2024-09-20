package backend.academy.model;

import backend.academy.model.word.Word;
import backend.academy.view.GameInfo;
import lombok.Getter;

class GameState {

    private final Alphabet alphabet = new Alphabet();
    private final Difficulty difficulty;
    private final String theme;
    @Getter
    private final Word word;
    private int attemptsLeft;

    GameState(Difficulty difficulty, String theme, Word word) {
        this.difficulty = difficulty;
        this.theme = theme;
        this.word = word;
        this.attemptsLeft = difficulty.attempts();
    }

    boolean isWin() {
        return word.isGuessed();
    }

    boolean isLoose() {
        return attemptsLeft == 0;
    }

    void guessLetter(String letter) {
        if (!word.guessLetter(letter)) {
            attemptsLeft--;
        }
        alphabet.makeLetterAsUsed(letter);
    }

    GameInfo getGameInfo() {
        return new GameInfo(word.toString(), attemptsLeft, difficulty.attempts(), theme, alphabet.toString());
    }
}
