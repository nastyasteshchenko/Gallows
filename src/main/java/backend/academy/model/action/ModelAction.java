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
        enteredLetterAction.gameLooseAction(viewAction.gameOverAction()::performGameLooseAction);
        enteredLetterAction.gameWinAction(viewAction.gameOverAction()::performGameWinAction);
        enteredLetterAction.drawGameAction(viewAction.drawGameAction()::performAction);
        enteredLetterAction.alreadyUsedLetterAction(viewAction.alreadyUsedLetterAction()::performAction);
        enteredLetterAction.notInAlphabetAction(viewAction.notInAlphabetAction()::performAction);
        enteredLetterAction.guessLetterAction(viewAction.guessLetterAction()::performAction);

        startNewGameAction.errorAction(viewAction.errorAction()::performAction);
        startNewGameAction.chooseThemeAction(viewAction.chooseThemeAction()::performAction);
        startNewGameAction.chooseDifficultyAction(viewAction.chooseDifficultyAction()::performAction);
        startNewGameAction.guessLetterAction(viewAction.guessLetterAction()::performAction);
        startNewGameAction.drawGameAction(viewAction.drawGameAction()::performAction);
        log.info("GameAction was initialized.");
    }
}
