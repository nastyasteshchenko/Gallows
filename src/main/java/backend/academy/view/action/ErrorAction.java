package backend.academy.view.action;

import java.io.PrintStream;

public class ErrorAction {

    private final PrintStream out = System.out;

    public void performAction(String message) {
        out.println(message);
    }
}
