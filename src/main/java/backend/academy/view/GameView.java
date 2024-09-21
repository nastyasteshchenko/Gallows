package backend.academy.view;

import backend.academy.model.listener.AlreadyUsedLetterListener;
import backend.academy.model.listener.DrawGameListener;
import backend.academy.model.listener.ErrorListener;
import backend.academy.model.listener.GameLooseListener;
import backend.academy.model.listener.GameWinListener;
import backend.academy.model.listener.GuessLetterListener;
import backend.academy.model.listener.NotInAlphabetListener;
import backend.academy.view.listener.ContinueGameListener;
import backend.academy.view.listener.EnterLetterListener;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Scanner;

public class GameView implements DrawGameListener, GameLooseListener, GameWinListener, GuessLetterListener,
    AlreadyUsedLetterListener, NotInAlphabetListener, ErrorListener {

    private static final Logger LOGGER = LogManager.getLogger(GameView.class);

    private static final String GAME_LOOSE_MSG = "You lost! The word was: ";
    private static final String GAME_WIN_MSG = "You won!";
    private static final String GAME_CONT_MSG =
        "Would you like to play again? Press Enter to continue or any other key to exit.";
    private static final String ALREADY_USED_LETTER_MSG = "You already used this letter. Try again.";
    private static final String NOT_IN_ALPHABET_MSG = "This letter is not in the alphabet. Try again.";

    @Setter
    private EnterLetterListener enterLetterListener;
    @Setter
    private ContinueGameListener continueGameListener;

    private final Scanner in = new Scanner(System.in);

    @Override
    public void onDrawGame(GameInfo gameInfo) {
        ConsoleCleaningUtility.clearConsole();
        System.out.println("Theme: " + gameInfo.theme());
        System.out.println("Attempts left: " + gameInfo.attemptsLeft());
        GallowsImage gallowsImage =
            GallowsImage.getByMistakesAmountAndDifficulty(
                gameInfo.totalAttempts() - gameInfo.attemptsLeft(), gameInfo.totalAttempts());
        LOGGER.debug("Gallows image: " + gallowsImage.name());
        System.out.println(gallowsImage.image());
        System.out.println(gameInfo.wordLetters());
        System.out.println();
        System.out.println("Possible letters: " + gameInfo.alphabet());
        System.out.println();
    }

    @Override
    public void onGameLoose(String word) {
        System.out.println(GAME_LOOSE_MSG + word);
        askIfContinue();
    }

    @Override
    public void onGameWin() {
        System.out.println(GAME_WIN_MSG);
        askIfContinue();
    }

    @Override
    public void onGuessLetter() {
        LOGGER.info("Guess letter menu was opened.");
        System.out.print("Guess a letter: ");
        while (true) {
            String input = in.nextLine();
            LOGGER.debug("User entered:" + input);
            if (input.isEmpty()) {
                System.out.print("Enter a letter: ");
                continue;
            }
            if (input.length() > 1) {
                System.out.print("Enter only one letter: ");
                continue;
            }
            LOGGER.info("Guess letter menu was closed.");
            if (enterLetterListener != null) {
                enterLetterListener.onEnterLetter(input);
            }
            break;
        }
    }

    @Override
    public void onAlreadyUsedLetter() {
        System.out.println(ALREADY_USED_LETTER_MSG);
        onGuessLetter();
    }

    @Override
    public void onNotInAlphabet() {
        System.out.println(NOT_IN_ALPHABET_MSG);
        onGuessLetter();
    }

    private void askIfContinue() {
        LOGGER.info("Continue game menu was opened.");
        System.out.println();
        System.out.println(GAME_CONT_MSG);
        String input = in.nextLine();
        LOGGER.debug("User entered: " + input);
        LOGGER.info("Continue game menu was closed.");
        if (input.isEmpty()) {
            if (continueGameListener != null) {
                continueGameListener.onContinueGame();
            }
        }
    }

    @Override
    public void onError(String message) {
        System.out.println(message);
    }
}
