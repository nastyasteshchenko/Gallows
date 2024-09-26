package backend.academy.view.action;

import backend.academy.view.ConsoleCleaningUtility;
import backend.academy.view.PrintListUtility;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChooseDifficultyAction implements Function<List<String>, String> {

    private final static String CHOOSE_DIFFICULTY_MSG = "Select a difficulty:";
    private final static String ENTER_NUMBER_MSG =
        "Enter the number of your preferred difficulty or just press Enter if it doesn't matter: ";
    private final static String NON_NUMERIC_VALUE_MSG = "Non-numeric value entered. Try again: ";
    private final static String OUT_OF_RANGE_MSG = "Number out of range. Try again: ";
    private final static String MENU_CLOSED_MSG = "Choose theme menu was closed.";

    private final PrintStream out = System.out;
    private final Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);

    @Override
    public String apply(List<String> possibleDifficulties) {
        log.info("Choose difficulty menu was opened.");
        ConsoleCleaningUtility.clearConsole();
        out.println(CHOOSE_DIFFICULTY_MSG);
        PrintListUtility.printNumberedList(possibleDifficulties);
        out.print(ENTER_NUMBER_MSG);

        while (true) {
            String input = in.nextLine();
            log.debug("User entered: " + input);
            if (input.isEmpty()) {
                log.info(MENU_CLOSED_MSG);
                return input;
            }
            try {
                int number = Integer.parseInt(input);
                if (number > possibleDifficulties.size()) {
                    out.print(OUT_OF_RANGE_MSG);
                    continue;
                }
                log.info(MENU_CLOSED_MSG);
                return possibleDifficulties.get(number - 1);
            } catch (NumberFormatException e) {
                out.print(NON_NUMERIC_VALUE_MSG);
            }
        }
    }
}
