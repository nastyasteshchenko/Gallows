package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.GameView;
import backend.academy.view.View;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameInitializer {

    Controller init() {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();

        setModelListener(model, view);
        setViewListener(view.gameView(), model);
        setControllerListener(controller, model);
        return controller;
    }

    private static void setModelListener(Model model, View view) {
        model.chooseThemeListener(view.chooseThemeMenu());
        model.chooseDifficultyListener(view.chooseDifficultyMenu());
        model.drawGameListener(view.gameView());
        model.gameWinListener(view.gameView());
        model.gameLooseListener(view.gameView());
        model.guessLetterListener(view.gameView());
        model.alreadyUsedLetterListener(view.gameView());
        model.notInAlphabetListener(view.gameView());
        model.errorListener(view.gameView());
        log.info("Model listeners were set.");
    }

    private static void setViewListener(GameView gameView, Model model) {
        gameView.enterLetterListener(model);
        gameView.continueGameListener(model);
        log.info("View listeners were set.");
    }

    private static void setControllerListener(Controller controller, Model model) {
        controller.startNewGameListener(model);
        log.info("Controller listeners were set.");
    }
}
