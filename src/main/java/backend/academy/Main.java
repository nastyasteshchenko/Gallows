package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.ChoosingDifficultyMenu;
import backend.academy.view.ChoosingThemeMenu;
import backend.academy.view.GameView;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller();
        ChoosingThemeMenu choosingThemeMenu = new ChoosingThemeMenu();
        ChoosingDifficultyMenu choosingDifficultyMenu = new ChoosingDifficultyMenu();
        GameView gameView = new GameView();
        controller.startNewGameListener(model);
        model.chooseThemeListener(choosingThemeMenu);
        model.chooseDifficultyListener(choosingDifficultyMenu);
        model.drawGameListener(gameView);
        gameView.enterLetterListener(model);
        controller.startNewGame();
    }
}
