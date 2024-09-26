package backend.academy.model;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.listener.AlreadyUsedLetterListener;
import backend.academy.model.listener.ChooseDifficultyListener;
import backend.academy.model.listener.ChooseThemeListener;
import backend.academy.model.listener.DrawGameListener;
import backend.academy.model.listener.ErrorListener;
import backend.academy.model.listener.GameLooseListener;
import backend.academy.model.listener.GameWinListener;
import backend.academy.model.listener.GuessLetterListener;
import backend.academy.model.listener.NotInAlphabetListener;
import backend.academy.model.word.Word;
import backend.academy.view.listener.ContinueGameListener;
import backend.academy.view.listener.EnterLetterListener;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Model implements StartNewGameListener, EnterLetterListener, ContinueGameListener {

    private static final Logger LOGGER = LogManager.getLogger(Model.class);
    private static final String DICTIONARY_ERROR_MSG = "Failed to load dictionary.";
    private static final String LETTER_STR = "Letter ";

    @Setter
    private ChooseDifficultyListener chooseDifficultyListener;
    @Setter
    private ChooseThemeListener chooseThemeListener;
    @Setter
    private DrawGameListener drawGameListener;
    @Setter
    private GameWinListener gameWinListener;
    @Setter
    private GameLooseListener gameLooseListener;
    @Setter
    private GuessLetterListener guessLetterListener;
    @Setter
    private AlreadyUsedLetterListener alreadyUsedLetterListener;
    @Setter
    private NotInAlphabetListener notInAlphabetListener;
    @Setter
    private ErrorListener errorListener;

    private GameState currentGameState;
    private Dictionary dictionary;

    @Override
    public void onStartNewGame() {
        LOGGER.info("Starting new game.");
        if (dictionary == null) {
            dictionary = Dictionary.loadDictionary();
            if (dictionary == null) {
                errorListener.onError(DICTIONARY_ERROR_MSG);
                return;
            }
            LOGGER.info("Dictionary loaded.");
        }
        buildCurrentGameState();
        if (drawGameListener != null) {
            drawGameListener.onDrawGame(currentGameState.getGameInfo());
            if (guessLetterListener != null) {
                guessLetterListener.onGuessLetter();
            }
        }
    }

    @Override
    public void onContinueGame() {
        onStartNewGame();
    }

    @Override
    public void onEnterLetter(String letter) {
        boolean shouldContinue = true;
        if (!currentGameState.isInAlphabet(letter)) {
            if (notInAlphabetListener != null) {
                LOGGER.debug(LETTER_STR + letter + " is not in the alphabet.");
                notInAlphabetListener.onNotInAlphabet();
            }
            shouldContinue = false;
        } else if (currentGameState.isAlreadyUsed(letter)) {
            if (alreadyUsedLetterListener != null) {
                LOGGER.debug(LETTER_STR + letter + " has already been used.");
                alreadyUsedLetterListener.onAlreadyUsedLetter();
            }
            shouldContinue = false;
        }
        if (!shouldContinue) {
            return;
        }
        currentGameState.guessLetter(letter);
        if (drawGameListener != null) {
            drawGameListener.onDrawGame(currentGameState.getGameInfo());
        }
        if (currentGameState.isLoose()) {
            if (gameLooseListener != null) {
                LOGGER.debug("Game was lost.");
                gameLooseListener.onGameLoose(currentGameState.word().getWord());
            }
            shouldContinue = false;
        } else if (currentGameState.isWin()) {
            if (gameWinListener != null) {
                LOGGER.debug("Game was won.");
                gameWinListener.onGameWin();
            }
            shouldContinue = false;
        }
        if (guessLetterListener != null && shouldContinue) {
            guessLetterListener.onGuessLetter();
        }
    }

    private void buildCurrentGameState() {
        String currentTheme = chooseTheme();
        Difficulty currentDifficulty = chooseDifficulty();
        if (currentDifficulty == null) {
            currentDifficulty = Difficulty.getRandomDifficulty();
        }
        String currentWord = dictionary.getRandomWord(currentTheme, currentDifficulty);
        LOGGER.debug("Current difficulty: " + currentDifficulty + System.lineSeparator()
            + "Current theme: " + currentTheme + System.lineSeparator()
            + "Current word: " + currentWord);
        currentGameState = new GameState(currentDifficulty, currentTheme, new Word(currentWord));
    }

    private Difficulty chooseDifficulty() {
        if (chooseDifficultyListener != null) {
            String chosenDifficulty = chooseDifficultyListener.onChooseDifficulty(Difficulty.getAllDifficulties());
            if (!chosenDifficulty.isEmpty()) {
                return Difficulty.getByName(chosenDifficulty);
            }
        }
        return Difficulty.getRandomDifficulty();
    }

    private String chooseTheme() {
        if (chooseThemeListener != null) {
            String chosenTheme = chooseThemeListener.onChooseTheme(dictionary.getThemes());
            if (!chosenTheme.isEmpty()) {
                return chosenTheme;
            }
        }
        return dictionary.getRandomTheme();
    }
}
