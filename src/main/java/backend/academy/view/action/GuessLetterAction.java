package backend.academy.view.action;

import java.util.function.Consumer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
public class GuessLetterAction extends Action implements Runnable {

    private Consumer<String> enterLetterAction;

    @Override
    public void run() {
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
