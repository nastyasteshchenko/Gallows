package backend.academy.view.action;

import lombok.Setter;

@Setter
public class NotInAlphabetAction extends Action implements Runnable {

    private static final String NOT_IN_ALPHABET_MSG = "This letter is not in the alphabet. Try again.";

    private Runnable guessLetterAction;

    @Override
    public void run() {
        out.println(NOT_IN_ALPHABET_MSG);
        if (guessLetterAction != null) {
            guessLetterAction.run();
        }
    }
}
