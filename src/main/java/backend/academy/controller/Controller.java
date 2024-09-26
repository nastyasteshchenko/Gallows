package backend.academy.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Controller {

    private Runnable startNewGameAction;

    public void initialize(Runnable startNewGameAction) {
        this.startNewGameAction = startNewGameAction;
        log.info("Controller was initialized.");
    }

    public void startNewGame() {
        if (startNewGameAction != null) {
            startNewGameAction.run();
        }
    }
}
