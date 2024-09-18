package backend.academy.controller;

import backend.academy.controller.listener.StartNewGameListener;
import lombok.Setter;

public class Controller {

    @Setter
    private StartNewGameListener startNewGameListener;

    public void startNewGame() {
        if (startNewGameListener != null) {
            startNewGameListener.onStartNewGame();
        }
    }
}
