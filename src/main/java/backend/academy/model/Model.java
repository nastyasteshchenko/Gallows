package backend.academy.model;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.listener.ChooseDifficultyListener;
import backend.academy.model.listener.ChooseThemeListener;
import backend.academy.view.listener.ChosenDifficultyListener;
import backend.academy.view.listener.ChosenThemeListener;
import lombok.Setter;
import java.io.IOException;

public class Model implements StartNewGameListener, ChosenThemeListener, ChosenDifficultyListener {

    @Setter
    private ChooseDifficultyListener chooseDifficultyListener;
    @Setter
    private ChooseThemeListener chooseThemeListener;
    private String currentTheme;
    private Difficulty currentDifficulty;
    private Dictionary dictionary;

    @Override
    public void onStartNewGame() {
        if (dictionary == null) {
            try {
                dictionary = Dictionary.loadDictionary();
                if (chooseThemeListener != null) {
                    chooseThemeListener.onChooseTheme(dictionary.getThemes());
                }
            } catch (IOException e) {
                //TODO: handle exception
            }
        }
    }

    @Override
    public void onChosenTheme(String chosenTheme) {
        if (chosenTheme.isEmpty()) {
            currentTheme = dictionary.getRandomTheme();
        } else {
            currentTheme = chosenTheme;
        }
        chooseDifficulty();
    }

    @Override
    public void onChosenDifficulty(String chosenDifficulty) {
        if (chosenDifficulty.isEmpty()) {
            currentDifficulty = Difficulty.getRandomDifficulty();
        } else {
            currentDifficulty = Difficulty.getByName(chosenDifficulty);
        }
    }

    public void chooseDifficulty() {
        if (chooseDifficultyListener != null) {
            chooseDifficultyListener.onChooseDifficulty(Difficulty.getAllDifficulties());
        }
    }
}
