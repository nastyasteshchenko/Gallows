package backend.academy.view;

import backend.academy.model.Model;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class View {

    private final ChooseDifficultyMenu chooseDifficultyMenu = new ChooseDifficultyMenu();
    private final ChooseThemeMenu chooseThemeMenu = new ChooseThemeMenu();
    private final GameView gameView = new GameView();

    public void initialize(Model model) {
        gameView.initialize(model);
        log.info("View listeners were set.");
    }
}
