package backend.academy.view;

import lombok.experimental.UtilityClass;
import java.io.PrintStream;
import java.util.List;

@UtilityClass
class PrintingListUtility {

    private final PrintStream out = System.out;

    void printNumberedList(List<String> list) {
        int i = 1;
        for (String s : list) {
            out.println(i++ + ". " + s);
        }
    }
}
