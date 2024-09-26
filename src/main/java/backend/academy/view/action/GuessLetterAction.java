package backend.academy.view.action;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.function.Consumer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuessLetterAction  {

    private final PrintStream out = System.out;
    private final Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    @Setter
    private Consumer<String> enterLetterAction;

    public void performAction() {
        log.info("Guess letter menu was opened.");
        out.print("Guess a letter: ");
        while (true) {
            String input = in.nextLine();
            log.debug("User entered:" + input);
            if (input.isEmpty()) {
                out.print("Enter a letter: ");
                continue;
            }
            if (input.length() > 1) {
                out.print("Enter only one letter: ");
                continue;
            }
            log.info("Guess letter menu was closed.");
            if (enterLetterAction != null) {
                enterLetterAction.accept(input);
            }
            break;
        }
    }
}
