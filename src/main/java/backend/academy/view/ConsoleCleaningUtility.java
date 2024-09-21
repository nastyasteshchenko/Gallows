package backend.academy.view;

import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
final class ConsoleCleaningUtility {

    static void clearConsole() throws IOException {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (InterruptedException ignored) {
        }
    }
}
