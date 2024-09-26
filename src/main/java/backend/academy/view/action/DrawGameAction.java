package backend.academy.view.action;

import backend.academy.view.ConsoleCleaningUtility;
import backend.academy.view.GallowsImage;
import backend.academy.view.GameInfo;
import java.io.PrintStream;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DrawGameAction implements Consumer<GameInfo> {

    private final PrintStream out = System.out;

    @Override
    public void accept(GameInfo gameInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        ConsoleCleaningUtility.clearConsole();
        stringBuilder.append("Theme: ").append(gameInfo.theme()).append(System.lineSeparator())
            .append("Attempts left: ").append(gameInfo.attemptsLeft()).append(System.lineSeparator());

        GallowsImage gallowsImage = GallowsImage.getByMistakesAmountAndDifficulty(
            gameInfo.totalAttempts() - gameInfo.attemptsLeft(), gameInfo.totalAttempts());
        log.debug("Gallows image: " + gallowsImage.name());
        stringBuilder.append(gallowsImage.image()).append(System.lineSeparator())
            .append(gameInfo.wordLetters()).append(System.lineSeparator()).append(System.lineSeparator())
            .append("Possible letters: ").append(gameInfo.alphabet()).append(System.lineSeparator());
        out.println(stringBuilder);
    }
}
