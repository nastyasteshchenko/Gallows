package backend.academy.view.action;

import java.io.PrintStream;
import lombok.Setter;

public class AlreadyUsedLetterAction {

    private static final String ALREADY_USED_LETTER_MSG = "You already used this letter. Try again.";

    private final PrintStream out = System.out;

    @Setter
    private Runnable guessLetterAction;

    public void performAction() {
        out.println(ALREADY_USED_LETTER_MSG);
        if (guessLetterAction != null) {
            guessLetterAction.run();
        }
    }
}
