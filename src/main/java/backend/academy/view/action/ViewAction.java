package backend.academy.view.action;

import backend.academy.model.action.ModelAction;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class ViewAction {

    private final AlreadyUsedLetterAction alreadyUsedLetterAction = new AlreadyUsedLetterAction();
    private final NotInAlphabetAction notInAlphabetAction = new NotInAlphabetAction();
    private final DrawGameAction drawGameAction = new DrawGameAction();
    private final GameOverAction gameOverAction = new GameOverAction();
    private final GuessLetterAction guessLetterAction = new GuessLetterAction();
    private final ErrorAction errorAction = new ErrorAction();
    private final ChooseThemeAction chooseThemeAction = new ChooseThemeAction();
    private final ChooseDifficultyAction chooseDifficultyAction = new ChooseDifficultyAction();

    public void initialize(ModelAction modelAction) {
        gameOverAction.startNewGameAction(modelAction.startNewGameAction());
        alreadyUsedLetterAction.guessLetterAction(guessLetterAction);
        notInAlphabetAction.guessLetterAction(guessLetterAction);
        guessLetterAction.enterLetterAction(modelAction.enteredLetterAction());
        log.info("ViewAction was initialized.");
    }
}
