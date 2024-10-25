package backend.academy.view.action;

import java.util.function.Consumer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Setter
@Slf4j
public class GameOverAction extends Action implements Runnable, Consumer<String> {

    private static final String GAME_LOOSE_MSG = "You lost! The word was: ";
    private static final String GAME_WIN_MSG = "You won!";
    private static final String GAME_CONT_MSG =
        "Would you like to play again? Press Enter to continue or any other key to exit.";

    private Runnable startNewGameAction;

    @Override
    public void accept(String word) {
        out.println(GAME_LOOSE_MSG + word);
        askIfContinue();
    }

    @Override
    public void run() {
        out.println(GAME_WIN_MSG);
        askIfContinue();
    }

    private void askIfContinue() {
        log.info("Continue game menu was opened.");
        out.println();
        out.println(GAME_CONT_MSG);
        String input = in.nextLine();
        log.debug("User entered: " + input);
        log.info("Continue game menu was closed.");
        if (input.isEmpty()) {
            if (startNewGameAction != null) {
                startNewGameAction.run();
            }
        }
    }
}
