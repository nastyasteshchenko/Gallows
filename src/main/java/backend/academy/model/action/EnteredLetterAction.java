package backend.academy.model.action;

import backend.academy.model.Model;
import backend.academy.view.GameInfo;
import java.util.function.Consumer;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class EnteredLetterAction implements Consumer<String> {

    private final Model model;

    private static final String LETTER_STR = "Letter ";

    private Consumer<String> gameLooseAction;
    private Consumer<GameInfo> drawGameAction;
    private Runnable notInAlphabetAction;
    private Runnable alreadyUsedLetterAction;
    private Runnable gameWinAction;
    private Runnable guessLetterAction;

    public EnteredLetterAction(Model model) {
        this.model = model;
    }

    @Override
    public void accept(String letter) {
        boolean shouldContinue = true;
        if (!model.currentGameState().isInAlphabet(letter)) {
            if (notInAlphabetAction != null) {
                log.debug(LETTER_STR + letter + " is not in the alphabet.");
                notInAlphabetAction.run();
            }
            shouldContinue = false;
        } else if (model.currentGameState().isAlreadyUsed(letter)) {
            if (alreadyUsedLetterAction != null) {
                log.debug(LETTER_STR + letter + " has already been used.");
                alreadyUsedLetterAction.run();
            }
            shouldContinue = false;
        }
        if (!shouldContinue) {
            return;
        }
        model.currentGameState().guessLetter(letter);
        if (drawGameAction != null) {
            drawGameAction.accept(model.currentGameState().getGameInfo());
        }
        if (model.currentGameState().isLoose()) {
            if (gameLooseAction != null) {
                log.debug("Game was lost.");
                gameLooseAction.accept(model.currentGameState().word().getWord());
            }
            shouldContinue = false;
        } else if (model.currentGameState().isWin()) {
            if (gameWinAction != null) {
                log.debug("Game was won.");
                gameWinAction.run();
            }
            shouldContinue = false;
        }
        if (guessLetterAction != null && shouldContinue) {
            guessLetterAction.run();
        }
    }
}
