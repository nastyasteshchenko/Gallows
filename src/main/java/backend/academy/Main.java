package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.GameView;
import backend.academy.view.View;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@UtilityClass
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Program was started.");
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();

        setModelListener(model, view);
        setViewListener(view.gameView(), model);
        setControllerListener(controller, model);

        controller.startNewGame();
        LOGGER.info("Program was finished.");
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
