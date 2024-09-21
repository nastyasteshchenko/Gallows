package backend.academy.view;

import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.PrintStream;

@UtilityClass
final class ConsoleCleaningUtility {

    private static final Logger LOGGER = LogManager.getLogger(ConsoleCleaningUtility.class);
    private final PrintStream out = System.out;

    static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.error(e.getMessage());
            out.println("Something went wrong while clearing the console.");
        }
    }
}
