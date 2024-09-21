package backend.academy.view;

import java.io.PrintStream;
import java.util.List;
import lombok.experimental.UtilityClass;

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
