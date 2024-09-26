package backend.academy.view.action;

import java.io.PrintStream;
import lombok.Setter;

public class NotInAlphabetAction implements Runnable {

    private static final String NOT_IN_ALPHABET_MSG = "This letter is not in the alphabet. Try again.";

    private final PrintStream out = System.out;

    @Setter
    private Runnable guessLetterAction;

    @Override
    public void run() {
        out.println(NOT_IN_ALPHABET_MSG);
        if (guessLetterAction != null) {
            guessLetterAction.run();
        }
    }
}
