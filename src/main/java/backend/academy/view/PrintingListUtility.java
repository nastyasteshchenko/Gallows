package backend.academy.view;

import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
class PrintingListUtility {

    void printNumberedList(List<String> list) {
        int i = 1;
        for (String s : list) {
            System.out.println(i++ + ". " + s);
        }
    }
}
