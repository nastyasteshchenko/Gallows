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
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private final PrintStream out = System.out;
    private final Scanner in = new Scanner(System.in,  StandardCharsets.UTF_8);
    private final StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void onDrawGame(GameInfo gameInfo) {
        stringBuilder.setLength(0);
        ConsoleCleaningUtility.clearConsole();
        stringBuilder.append("Theme: ").append(gameInfo.theme()).append(System.lineSeparator())
                .append("Attempts left: ").append(gameInfo.attemptsLeft()).append(System.lineSeparator());
        GallowsImage gallowsImage =
                GallowsImage.getByMistakesAmountAndDifficulty(
                        gameInfo.totalAttempts() - gameInfo.attemptsLeft(), gameInfo.totalAttempts());
        LOGGER.debug("Gallows image: " + gallowsImage.name());
        stringBuilder.append(gallowsImage.image()).append(System.lineSeparator())
                .append(gameInfo.wordLetters()).append(System.lineSeparator()).append(System.lineSeparator())
                .append("Possible letters: ").append(gameInfo.alphabet()).append(System.lineSeparator());
        out.println(stringBuilder);
    }

    @Override
    public void onGameLoose(String word) {
        out.println(GAME_LOOSE_MSG + word);
        askIfContinue();
    }

    @Override
    public void onGameWin() {
        out.println(GAME_WIN_MSG);
        askIfContinue();
    }

    @Override
    public void onGuessLetter() {
        LOGGER.info("Guess letter menu was opened.");
        out.print("Guess a letter: ");
        while (true) {
            String input = in.nextLine();
            LOGGER.debug("User entered:" + input);
            if (input.isEmpty()) {
                out.print("Enter a letter: ");
                continue;
            }
            if (input.length() > 1) {
                out.print("Enter only one letter: ");
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
        out.println(ALREADY_USED_LETTER_MSG);
        onGuessLetter();
    }

    @Override
    public void onNotInAlphabet() {
        out.println(NOT_IN_ALPHABET_MSG);
        onGuessLetter();
    }

    private void askIfContinue() {
        LOGGER.info("Continue game menu was opened.");
        out.println();
        out.println(GAME_CONT_MSG);
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
        out.println(message);
    }
}
