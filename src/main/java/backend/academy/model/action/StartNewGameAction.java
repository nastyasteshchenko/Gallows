package backend.academy.model.action;

import backend.academy.model.Dictionary;
import backend.academy.model.Difficulty;
import backend.academy.model.GameState;
import backend.academy.model.Model;
import backend.academy.model.word.Word;
import backend.academy.view.GameInfo;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class StartNewGameAction implements Runnable {

    private static final String DICTIONARY_ERROR_MSG = "Failed to load dictionary.";

    private final Model model;

    private Consumer<String> errorAction;
    private Consumer<GameInfo> drawGameAction;
    private Function<List<String>, String> chooseDifficultyAction;
    private Function<List<String>, String> chooseThemeAction;
    private Runnable guessLetterAction;

    public StartNewGameAction(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        log.info("Starting new game.");
        if (model.dictionary() == null) {
            model.dictionary(Dictionary.loadDictionary());
            if (model.dictionary() == null) {
                if (errorAction != null) {
                    errorAction.accept(DICTIONARY_ERROR_MSG);
                }
                return;
            }
            log.info("Dictionary loaded.");
        }
        buildCurrentGameState();
        if (drawGameAction != null) {
            drawGameAction.accept(model.currentGameState().getGameInfo());
            if (guessLetterAction != null) {
                guessLetterAction.run();
            }
        }
    }

    private void buildCurrentGameState() {
        String currentTheme = chooseTheme();
        Difficulty currentDifficulty = chooseDifficulty();
        if (currentDifficulty == null) {
            currentDifficulty = Difficulty.getRandomDifficulty();
        }
        String currentWord = model.dictionary().getRandomWord(currentTheme, currentDifficulty);
        log.debug("Current difficulty: " + currentDifficulty + System.lineSeparator()
            + "Current theme: " + currentTheme + System.lineSeparator()
            + "Current word: " + currentWord);
        model.currentGameState(new GameState(currentDifficulty, currentTheme, new Word(currentWord)));
    }

    private Difficulty chooseDifficulty() {
        if (chooseDifficultyAction != null) {
            String chosenDifficulty = chooseDifficultyAction.apply(Difficulty.getAllDifficulties());
            if (!chosenDifficulty.isEmpty()) {
                return Difficulty.getByName(chosenDifficulty);
            }
        }
        return Difficulty.getRandomDifficulty();
    }

    private String chooseTheme() {
        if (chooseThemeAction != null) {
            String chosenTheme = chooseDifficultyAction.apply(model.dictionary().getThemes());
            if (!chosenTheme.isEmpty()) {
                return chosenTheme;
            }
        }
        return model.dictionary().getRandomTheme();
    }
}
