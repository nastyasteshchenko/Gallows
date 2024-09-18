package backend.academy.model;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.listener.ChooseDifficultyListener;
import backend.academy.model.listener.ChooseThemeListener;
import lombok.Setter;
import java.io.IOException;

public class Model implements StartNewGameListener {

    @Setter
    private ChooseDifficultyListener chooseDifficultyListener;
    @Setter
    private ChooseThemeListener chooseThemeListener;
    private GameConfig currentConfig;
    private Dictionary dictionary;

    @Override
    public void onStartNewGame() {
        if (dictionary == null) {
            try {
                dictionary = Dictionary.loadDictionary();
                String currentTheme = chooseTheme();
                Difficulty currentDifficulty = chooseDifficulty();
                String currentWord = dictionary.getRandomWord(currentTheme, currentDifficulty);
                currentConfig = new GameConfig(currentDifficulty, currentTheme, currentWord);
            } catch (IOException e) {
                //TODO: handle exception
            }
        }
    }

    public Difficulty chooseDifficulty() {
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
}
