package backend.academy;

import lombok.experimental.UtilityClass;
import java.io.IOException;

@UtilityClass
public final class ConsoleCleaningUtility {

    public static void clearConsole() throws IOException {
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
