package backend.academy.view.action;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameOverAction {

    private static final String GAME_LOOSE_MSG = "You lost! The word was: ";
    private static final String GAME_WIN_MSG = "You won!";
    private static final String GAME_CONT_MSG =
        "Would you like to play again? Press Enter to continue or any other key to exit.";

    private final PrintStream out = System.out;
    private final Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    @Setter
    private Runnable startNewGameAction;

    public void performGameLooseAction(String word) {
        out.println(GAME_LOOSE_MSG + word);
        askIfContinue();
    }

    public void performGameWinAction() {
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
