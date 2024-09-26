package backend.academy.model;

import backend.academy.model.word.Word;
import backend.academy.view.GameInfo;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameState {

    private static final String LETTER = "Letter ";

    @Getter
    private final Word word;
    private final Alphabet alphabet = new Alphabet();
    private final Difficulty difficulty;
    private final String theme;
    private int attemptsLeft;

    public GameState(Difficulty difficulty, String theme, Word word) {
        this.difficulty = difficulty;
        this.theme = theme;
        this.word = word;
        this.attemptsLeft = difficulty.attempts();
    }

    public boolean isWin() {
        return word.isGuessed();
    }

    public boolean isLoose() {
        return attemptsLeft == 0;
    }

    public boolean isAlreadyUsed(String letter) {
        return alphabet.isAlreadyUsed(letter);
    }

    public boolean isInAlphabet(String letter) {
        return alphabet.isInAlphabet(letter);
    }

    public void guessLetter(String letter) {
        if (!word.guessLetter(letter)) {
            log.debug(LETTER + letter + " is not in the word.");
            attemptsLeft--;
        } else {
            log.debug(LETTER + letter + " is in the word.");
        }
        alphabet.makeLetterAsUsed(letter);
    }

    public GameInfo getGameInfo() {
        return new GameInfo(word.toString(), attemptsLeft, difficulty.attempts(), theme, alphabet.toString());
    }
}
