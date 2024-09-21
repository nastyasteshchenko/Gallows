package backend.academy.view;

import backend.academy.model.listener.ChooseThemeListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

class ChoosingThemeMenu implements ChooseThemeListener {

    private static final Logger LOGGER = LogManager.getLogger(ChoosingThemeMenu.class);

    private final static String CHOOSE_THEME_MSG = "Select a theme:";
    private final static String ENTER_NUMBER_MSG =
            "Enter the number of your preferred theme or just press Enter if it doesn't matter: ";
    private final static String NON_NUMERIC_VALUE_MSG = "Non-numeric value entered. Try again: ";
    private final static String OUT_OF_RANGE_MSG = "Number out of range. Try again: ";
    private final static String MENU_CLOSED_MSG = "Choose theme menu was closed.";

    private final PrintStream out = System.out;
    private final Scanner in = new Scanner(System.in);

    @Override
    public String onChooseTheme(List<String> possibleThemes) {
        LOGGER.info("Choose theme menu was opened.");
        ConsoleCleaningUtility.clearConsole();
        out.println(CHOOSE_THEME_MSG);
        PrintingListUtility.printNumberedList(possibleThemes);
        out.print(ENTER_NUMBER_MSG);

        while (true) {
            String input = in.nextLine();
            LOGGER.debug("User entered: " + input);
            if (input.isEmpty()) {
                LOGGER.info(MENU_CLOSED_MSG);
                return input;
            }
            try {
                int number = Integer.parseInt(input);
                if (number > possibleThemes.size()) {
                    out.print(OUT_OF_RANGE_MSG);
                    continue;
                }
                LOGGER.info(MENU_CLOSED_MSG);
                return possibleThemes.get(number - 1);
            } catch (NumberFormatException e) {
                out.print(NON_NUMERIC_VALUE_MSG);
            }
        }
    }
}
