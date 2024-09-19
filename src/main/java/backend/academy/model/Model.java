package backend.academy.model;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.listener.ChooseDifficultyListener;
import backend.academy.model.listener.ChooseThemeListener;
import backend.academy.model.listener.DrawGameListener;
import backend.academy.model.word.Word;
import backend.academy.view.listener.EnterLetterListener;
import lombok.Setter;
import java.io.IOException;

public class Model implements StartNewGameListener, EnterLetterListener {

    @Setter
    private ChooseDifficultyListener chooseDifficultyListener;
    @Setter
    private ChooseThemeListener chooseThemeListener;
    @Setter
    private DrawGameListener drawGameListener;
    private GameState currentGameState;
    private int attemptsLast;
    private Dictionary dictionary;

    @Override
    public void onStartNewGame() {
        if (dictionary == null) {
            try {
                dictionary = Dictionary.loadDictionary();
                String currentTheme = chooseTheme();
                Difficulty currentDifficulty = chooseDifficulty();
                String currentWord = dictionary.getRandomWord(currentTheme, currentDifficulty);
                System.out.println(currentWord);
                currentGameState = new GameState(currentDifficulty, currentTheme, new Word(currentWord));
                attemptsLast = currentDifficulty.attempts();
                if (drawGameListener != null) {
                    drawGameListener.onDrawGame(currentGameState.word().getLetters(), 0,
                        attemptsLast, currentTheme);
                }
            } catch (IOException e) {
                //TODO: handle exception
            }
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
    public void onEnterLetter(String letter) {
        if (!currentGameState.word().guessLetter(letter)) {
            attemptsLast--;
        }
        //TODO:
        if (drawGameListener != null) {
            drawGameListener.onDrawGame(currentGameState.word().getLetters(),
                currentGameState.difficulty().attempts() - attemptsLast,
                currentGameState.difficulty().attempts(), currentGameState.theme());
        }
    }
}
