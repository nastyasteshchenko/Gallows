package backend.academy.model.action;

import backend.academy.model.Model;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuessLetterAction implements Consumer<String> {

    private static final String LETTER = "Letter ";

    private final Model model;

    public GuessLetterAction(Model model) {
        this.model = model;
    }

    public void accept(String letter) {
        if (!model.currentGameState().word().guessLetter(letter)) {
            log.debug(LETTER + letter + " is not in the word.");
            model.currentGameState().decreaseAttempts();
        } else {
            log.debug(LETTER + letter + " is in the word.");
        }
        model.currentGameState().alphabet().makeLetterAsUsed(letter);
    }
}
