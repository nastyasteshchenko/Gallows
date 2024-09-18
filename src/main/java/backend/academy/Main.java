package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.ChoosingThemeMenu;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller();
        ChoosingThemeMenu choosingThemeMenu = new ChoosingThemeMenu();
        choosingThemeMenu.chosenThemeListener(model);
        controller.startNewGameListener(model);
        model.chooseThemeListener(choosingThemeMenu);
        controller.startNewGame();
    }
}
