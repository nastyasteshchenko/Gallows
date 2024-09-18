package backend.academy.model;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.listener.ChooseThemeListener;
import backend.academy.view.listener.ChosenThemeListener;
import lombok.Setter;
import java.io.IOException;

public class Model implements StartNewGameListener, ChosenThemeListener {

    @Setter
    private ChooseThemeListener chooseThemeListener;
    private String currentTheme;
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
        currentTheme = chosenTheme;
    }
}
