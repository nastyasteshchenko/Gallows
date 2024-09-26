package backend.academy.view.action;

import lombok.Setter;

@Setter
public class AlreadyUsedLetterAction extends Action implements Runnable {

    private static final String ALREADY_USED_LETTER_MSG = "You already used this letter. Try again.";

    private Runnable guessLetterAction;

    @Override
    public void run() {
        out.println(ALREADY_USED_LETTER_MSG);
        if (guessLetterAction != null) {
            guessLetterAction.run();
        }
    }
}
