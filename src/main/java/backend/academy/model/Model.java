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
import backend.academy.view.View;
import backend.academy.view.listener.ContinueGameListener;
import backend.academy.view.listener.EnterLetterListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Model implements StartNewGameListener, EnterLetterListener, ContinueGameListener {

    private static final String DICTIONARY_ERROR_MSG = "Failed to load dictionary.";
    private static final String LETTER_STR = "Letter ";

    private ChooseDifficultyListener chooseDifficultyListener;
    private ChooseThemeListener chooseThemeListener;
    private DrawGameListener drawGameListener;
    private GameWinListener gameWinListener;
    private GameLooseListener gameLooseListener;
    private GuessLetterListener guessLetterListener;
    private AlreadyUsedLetterListener alreadyUsedLetterListener;
    private NotInAlphabetListener notInAlphabetListener;
    private ErrorListener errorListener;

    private GameState currentGameState;
    private Dictionary dictionary;

    public void initialize(View view) {
        chooseThemeListener = view.chooseThemeMenu();
        chooseDifficultyListener = view.chooseDifficultyMenu();
        drawGameListener = view.gameView();
        gameWinListener = view.gameView();
        gameLooseListener = view.gameView();
        guessLetterListener = view.gameView();
        alreadyUsedLetterListener = view.gameView();
        notInAlphabetListener = view.gameView();
        errorListener = view.gameView();
        log.info("Model listeners were set.");
    }

    @Override
    public void onStartNewGame() {
        log.info("Starting new game.");
        if (dictionary == null) {
            dictionary = Dictionary.loadDictionary();
            if (dictionary == null) {
                errorListener.onError(DICTIONARY_ERROR_MSG);
                return;
            }
            log.info("Dictionary loaded.");
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
                log.debug(LETTER_STR + letter + " is not in the alphabet.");
                notInAlphabetListener.onNotInAlphabet();
            }
            shouldContinue = false;
        } else if (currentGameState.isAlreadyUsed(letter)) {
            if (alreadyUsedLetterListener != null) {
                log.debug(LETTER_STR + letter + " has already been used.");
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
                log.debug("Game was lost.");
                gameLooseListener.onGameLoose(currentGameState.word().getWord());
            }
            shouldContinue = false;
        } else if (currentGameState.isWin()) {
            if (gameWinListener != null) {
                log.debug("Game was won.");
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
        log.debug("Current difficulty: " + currentDifficulty + System.lineSeparator()
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
