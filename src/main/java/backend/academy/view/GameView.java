package backend.academy.view;

import backend.academy.model.listener.DrawGameListener;
import backend.academy.view.listener.EnterLetterListener;
import lombok.Setter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class GameView implements DrawGameListener {

    @Setter
    private EnterLetterListener enterLetterListener;
    private final Scanner in = new Scanner(System.in);

    @Override
    public void onDrawGame(List<Character> wordLetters, int mistakes, int attempts, String theme) {
        clearScreen();
        System.out.println("Theme: " + theme);
        System.out.println(GallowsImage.getByMistakesAmountAndDifficulty(mistakes, attempts).image());
        wordLetters.forEach(letter -> System.out.print(letter + " "));
        System.out.println();
        System.out.println("Guess a letter: ");
        while (true) {
            String input = in.nextLine();
            if (input.isEmpty()) {
                System.out.println("Enter a letter: ");
                continue;
            }
            if (input.length() > 1) {
                System.out.println("Enter only one letter: ");
                continue;
            }
            enterLetterListener.onEnterLetter(input);
            break;
        }
    }

    private void clearScreen() {
        try {
            ConsoleCleaningUtility.clearConsole();
        } catch (IOException e) {
            //TODO: handle exception
        }
    }
}
