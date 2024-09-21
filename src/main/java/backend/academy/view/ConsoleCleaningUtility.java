package backend.academy.view;

import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

@UtilityClass
final class ConsoleCleaningUtility {

    private static final Logger LOGGER = LogManager.getLogger(ConsoleCleaningUtility.class);

    static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage());
            System.out.println("Something went wrong while clearing the console.");
        }
    }
}
