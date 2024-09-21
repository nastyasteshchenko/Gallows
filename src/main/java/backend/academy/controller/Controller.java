package backend.academy.controller;

import backend.academy.controller.listener.StartNewGameListener;
import lombok.Setter;

@Setter
public class Controller {

    private StartNewGameListener startNewGameListener;

    public void startNewGame() {
        if (startNewGameListener != null) {
            startNewGameListener.onStartNewGame();
        }
    }
}
