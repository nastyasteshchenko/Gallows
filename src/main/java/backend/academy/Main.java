package backend.academy;

import backend.academy.controller.Controller;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@UtilityClass
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Program was started.");
        GameInitializer gameInitializer = new GameInitializer();
        Controller controller = gameInitializer.init();
        controller.startNewGame();
        LOGGER.info("Program was finished.");
    }
}
