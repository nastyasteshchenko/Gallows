package backend.academy.view.action;

import java.io.PrintStream;
import java.util.function.Consumer;

public class ErrorAction extends Action implements Consumer<String> {

    private final PrintStream out = System.out;

    @Override
    public void accept(String message) {
        out.println(message);
    }
}
