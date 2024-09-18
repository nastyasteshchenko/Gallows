package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.ChoosingDifficultyMenu;
import backend.academy.view.ChoosingThemeMenu;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller();
        ChoosingThemeMenu choosingThemeMenu = new ChoosingThemeMenu();
        ChoosingDifficultyMenu choosingDifficultyMenu = new ChoosingDifficultyMenu();
        controller.startNewGameListener(model);
        model.chooseThemeListener(choosingThemeMenu);
        model.chooseDifficultyListener(choosingDifficultyMenu);

        controller.startNewGame();
    }
}
