package backend.academy.model;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.listener.AlreadyUsedLetterListener;
import backend.academy.model.listener.ChooseDifficultyListener;
import backend.academy.model.listener.ChooseThemeListener;
import backend.academy.model.listener.DrawGameListener;
import backend.academy.model.listener.GameLooseListener;
import backend.academy.model.listener.GameWinListener;
import backend.academy.model.listener.GuessLetterListener;
import backend.academy.model.listener.NotInAlphabetListener;
import backend.academy.model.word.Word;
import backend.academy.view.listener.ContinueGameListener;
import backend.academy.view.listener.EnterLetterListener;
import lombok.Setter;
import java.io.IOException;

public class Model implements StartNewGameListener, EnterLetterListener, ContinueGameListener {

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

    private GameState currentGameState;
    private Dictionary dictionary;

    @Override
    public void onStartNewGame() {
        if (dictionary == null) {
            try {
                dictionary = Dictionary.loadDictionary();
                String currentTheme = chooseTheme();
                Difficulty currentDifficulty = chooseDifficulty();
                String currentWord = dictionary.getRandomWord(currentTheme, currentDifficulty);
                currentGameState = new GameState(currentDifficulty, currentTheme, new Word(currentWord));
                if (drawGameListener != null) {
                    drawGameListener.onDrawGame(currentGameState.getGameInfo());
                    if (guessLetterListener != null) {
                        guessLetterListener.onGuessLetter();
                    }
                }
            } catch (IOException e) {
                //TODO: handle exception
            }
        }
    }

    @Override
    public void onEnterLetter(String letter) {
        if (!currentGameState.isInAlphabet(letter)) {
            if (notInAlphabetListener != null) {
                notInAlphabetListener.onNotInAlphabet();
            }
            return;
        }
        if (currentGameState.isAlreadyUsed(letter)) {
            if (alreadyUsedLetterListener != null) {
                alreadyUsedLetterListener.onAlreadyUsedLetter();
            }
            return;
        }
        currentGameState.guessLetter(letter);
        if (drawGameListener != null) {
            drawGameListener.onDrawGame(currentGameState.getGameInfo());
        }
        if (currentGameState.isLoose()) {
            if (gameLooseListener != null) {
                gameLooseListener.onGameLoose(currentGameState.word().getWord());
            }
            return;
        }
        if (currentGameState.isWin()) {
            if (gameWinListener != null) {
                gameWinListener.onGameWin();
            }
            return;
        }
        if (guessLetterListener != null) {
            guessLetterListener.onGuessLetter();
        }
    }

    private Difficulty chooseDifficulty() {
        if (chooseDifficultyListener != null) {
            String chosenDifficulty = chooseDifficultyListener.onChooseDifficulty(Difficulty.getAllDifficulties());
            return chosenDifficulty.isEmpty() ? Difficulty.getRandomDifficulty() :
                Difficulty.getByName(chosenDifficulty);
        }
        return null;
    }

    private String chooseTheme() {
        if (chooseThemeListener != null) {
            String chosenTheme = chooseThemeListener.onChooseTheme(dictionary.getThemes());
            return chosenTheme.isEmpty() ? dictionary.getRandomTheme() : chosenTheme;
        }
        return null;
    }

    @Override
    public void onContinueGame() {
        onStartNewGame();
    }
}
