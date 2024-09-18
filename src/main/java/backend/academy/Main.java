package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.ChoosingDifficultyMenu;
import backend.academy.view.ChoosingThemeMenu;
import backend.academy.view.ConsoleCleaningUtility;
import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
public class Main {
    public static void main(String[] args){
        Model model = new Model();
        Controller controller = new Controller();
        ChoosingThemeMenu choosingThemeMenu = new ChoosingThemeMenu();
        ChoosingDifficultyMenu choosingDifficultyMenu = new ChoosingDifficultyMenu();
        choosingThemeMenu.chosenThemeListener(model);
        controller.startNewGameListener(model);
        model.chooseThemeListener(choosingThemeMenu);
        choosingDifficultyMenu.chosenDifficultyListener(model);
        model.chooseDifficultyListener(choosingDifficultyMenu);

        controller.startNewGame();
    }
}
