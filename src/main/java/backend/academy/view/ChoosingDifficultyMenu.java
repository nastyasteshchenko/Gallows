package backend.academy.view;

import backend.academy.model.listener.ChooseDifficultyListener;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ChoosingDifficultyMenu implements ChooseDifficultyListener {

    private final static String CHOOSE_DIFFICULTY_MSG = "Select a difficulty:";
    private final static String ENTER_NUMBER_MSG =
        "Enter the number of your preferred difficulty or just press Enter if it doesn't matter: ";
    private final static String NON_NUMERIC_VALUE_MSG = "Non-numeric value entered. Try again: ";
    private final static String OUT_OF_RANGE_MSG = "Number out of range. Try again: ";

    private final Scanner in = new Scanner(System.in);

    @Override
    public String onChooseDifficulty(List<String> possibleDifficulties) {
        clearScreen();
        System.out.println(CHOOSE_DIFFICULTY_MSG);
        PrintingListUtility.printNumberedList(possibleDifficulties);
        System.out.print(ENTER_NUMBER_MSG);

        while (true) {
            String input = in.nextLine();
            if (input.isEmpty()) {
                return input;
            }
            try {
                int number = Integer.parseInt(input);
                if (number > possibleDifficulties.size()) {
                    System.out.print(OUT_OF_RANGE_MSG);
                    continue;
                }
                return possibleDifficulties.get(number - 1);
            } catch (NumberFormatException e) {
                System.out.print(NON_NUMERIC_VALUE_MSG);
            }
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
