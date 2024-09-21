package backend.academy.view;

import lombok.Getter;

@Getter
public class View {

    private final ChoosingDifficultyMenu choosingDifficultyMenu = new ChoosingDifficultyMenu();
    private final ChoosingThemeMenu choosingThemeMenu = new ChoosingThemeMenu();
    private final GameView gameView = new GameView();
}
