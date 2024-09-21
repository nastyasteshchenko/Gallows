package backend.academy.model;

import backend.academy.model.word.Word;
import backend.academy.view.GameInfo;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class GameState {

    private static final Logger LOGGER = LogManager.getLogger(GameState.class);

    @Getter
    private final Word word;
    private final Alphabet alphabet = new Alphabet();
    private final Difficulty difficulty;
    private final String theme;
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

    boolean isAlreadyUsed(String letter) {
        return alphabet.isAlreadyUsed(letter);
    }

    boolean isInAlphabet(String letter) {
        return alphabet.isInAlphabet(letter);
    }

    void guessLetter(String letter) {
        if (!word.guessLetter(letter)) {
            LOGGER.debug("Letter " + letter + " is not in the word.");
            attemptsLeft--;
        } else {
            LOGGER.debug("Letter " + letter + " is in the word.");
        }
        alphabet.makeLetterAsUsed(letter);
    }

    GameInfo getGameInfo() {
        return new GameInfo(word.toString(), attemptsLeft, difficulty.attempts(), theme, alphabet.toString());
    }
}
