package backend.academy.controller;

import backend.academy.controller.listener.StartNewGameListener;
import backend.academy.model.Model;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Controller {

    private StartNewGameListener startNewGameListener;

    public void initialize(Model model) {
        startNewGameListener = model;
        log.info("Controller listeners were set.");
    }

    public void startNewGame() {
        if (startNewGameListener != null) {
            startNewGameListener.onStartNewGame();
        }
    }
}
