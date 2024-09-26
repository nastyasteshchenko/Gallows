package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.GameView;
import backend.academy.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameInitializer {
    private static final Logger LOGGER = LogManager.getLogger(GameInitializer.class);

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
        LOGGER.info("Model listeners were set.");
    }

    private static void setViewListener(GameView gameView, Model model) {
        gameView.enterLetterListener(model);
        gameView.continueGameListener(model);
        LOGGER.info("View listeners were set.");
    }

    private static void setControllerListener(Controller controller, Model model) {
        controller.startNewGameListener(model);
        LOGGER.info("Controller listeners were set.");
    }
}
