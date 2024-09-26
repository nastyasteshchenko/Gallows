package backend.academy;

import backend.academy.controller.Controller;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class Main {

    public static void main(String[] args) {
        log.info("Program was started.");
        GameInitializer gameInitializer = new GameInitializer();
        Controller controller = gameInitializer.init();
        controller.startNewGame();
        log.info("Program was finished.");
    }
}
