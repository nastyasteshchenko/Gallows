package backend.academy.view;

import backend.academy.model.listener.AlreadyUsedLetterListener;
import backend.academy.model.listener.DrawGameListener;
import backend.academy.model.listener.GameLooseListener;
import backend.academy.model.listener.GameWinListener;
import backend.academy.model.listener.GuessLetterListener;
import backend.academy.model.listener.NotInAlphabetListener;
import backend.academy.view.listener.EnterLetterListener;
import lombok.Setter;
import java.io.IOException;
import java.util.Scanner;

public class GameView implements DrawGameListener, GameLooseListener, GameWinListener, GuessLetterListener,
    AlreadyUsedLetterListener, NotInAlphabetListener {

    @Setter
    private EnterLetterListener enterLetterListener;
    private static final String GAME_LOOSE_MSG = "You loose! The word was: ";
    private static final String GAME_WIN_MSG = "You won!";
    private final Scanner in = new Scanner(System.in);

    @Override
    public void onDrawGame(GameInfo gameInfo) {
        clearScreen();
        System.out.println("Theme: " + gameInfo.theme());
        System.out.println("Attempts left: " + gameInfo.attemptsLeft());
        GallowsImage gallowsImage =
            GallowsImage.getByMistakesAmountAndDifficulty(
                gameInfo.totalAttempts() - gameInfo.attemptsLeft(), gameInfo.totalAttempts());
        System.out.println(gallowsImage.image());
        System.out.println(gameInfo.wordLetters());
        System.out.println();
        System.out.println("Possible letters: " + gameInfo.alphabet());
        System.out.println();
    }

    @Override
    public void onGameLoose(String word) {
        System.out.println(GAME_LOOSE_MSG + word);
    }

    @Override
    public void onGameWin() {
        System.out.println(GAME_WIN_MSG);
    }

    @Override
    public void onGuessLetter() {
        System.out.print("Guess a letter: ");
        while (true) {
            String input = in.nextLine();
            if (input.isEmpty()) {
                System.out.print("Enter a letter: ");
                continue;
            }
            if (input.length() > 1) {
                System.out.print("Enter only one letter: ");
                continue;
            }
            enterLetterListener.onEnterLetter(input);
            break;
        }
    }

    @Override
    public void onAlreadyUsedLetter() {
        System.out.println("You already used this letter. Try again.");
        onGuessLetter();
    }

    @Override
    public void onNotInAlphabet() {
        System.out.println("This letter is not in the alphabet. Try again.");
        onGuessLetter();
    }

    private void clearScreen() {
        try {
            ConsoleCleaningUtility.clearConsole();
        } catch (IOException e) {
            //TODO: handle exception
        }
    }
}
