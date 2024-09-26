package backend.academy.view;

import java.io.IOException;
import java.io.PrintStream;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public final class ConsoleCleaningUtility {

    private final PrintStream out = System.out;

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                out.print("\033\143");
            }
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            out.println("Something went wrong while clearing the console.");
        }
    }
}
