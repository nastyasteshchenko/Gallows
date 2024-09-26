package backend.academy.view;

import lombok.Getter;

@Getter
public class View {

    private final ChooseDifficultyMenu chooseDifficultyMenu = new ChooseDifficultyMenu();
    private final ChooseThemeMenu chooseThemeMenu = new ChooseThemeMenu();
    private final GameView gameView = new GameView();
}
