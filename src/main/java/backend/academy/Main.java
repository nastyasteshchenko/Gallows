package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.GameView;
import backend.academy.view.View;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();

        setModelListener(model, view);
        setViewListener(view.gameView(), model);
        controller.startNewGameListener(model);

        controller.startNewGame();
    }

    private static void setModelListener(Model model, View view) {
        model.chooseThemeListener(view.choosingThemeMenu());
        model.chooseDifficultyListener(view.choosingDifficultyMenu());
        model.drawGameListener(view.gameView());
        model.gameWinListener(view.gameView());
        model.gameLooseListener(view.gameView());
        model.guessLetterListener(view.gameView());
        model.alreadyUsedLetterListener(view.gameView());
        model.notInAlphabetListener(view.gameView());
    }

    private static void setViewListener(GameView gameView, Model model) {
        gameView.enterLetterListener(model);
        gameView.continueGameListener(model);
    }
}
