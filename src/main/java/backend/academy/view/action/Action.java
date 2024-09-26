package backend.academy.view.action;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class Action {
    protected final PrintStream out = System.out;
    protected final Scanner in = new Scanner(System.in, StandardCharsets.UTF_8);
}
