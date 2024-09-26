package backend.academy.model.action;

import backend.academy.model.Model;
import backend.academy.view.action.ViewAction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ModelAction {

    private final EnteredLetterAction enteredLetterAction;
    private final StartNewGameAction startNewGameAction;

    public ModelAction(Model model) {
        enteredLetterAction = new EnteredLetterAction(model);
        startNewGameAction = new StartNewGameAction(model);
    }

    public void initialize(ViewAction viewAction) {
        enteredLetterAction.gameLooseAction(viewAction.gameOverAction());
        enteredLetterAction.gameWinAction(viewAction.gameOverAction());
        enteredLetterAction.drawGameAction(viewAction.drawGameAction());
        enteredLetterAction.alreadyUsedLetterAction(viewAction.alreadyUsedLetterAction());
        enteredLetterAction.notInAlphabetAction(viewAction.notInAlphabetAction());
        enteredLetterAction.guessLetterAction(viewAction.guessLetterAction());

        startNewGameAction.errorAction(viewAction.errorAction());
        startNewGameAction.chooseThemeAction(viewAction.chooseThemeAction());
        startNewGameAction.chooseDifficultyAction(viewAction.chooseDifficultyAction());
        startNewGameAction.guessLetterAction(viewAction.guessLetterAction());
        startNewGameAction.drawGameAction(viewAction.drawGameAction());
        log.info("GameAction was initialized.");
    }
}
