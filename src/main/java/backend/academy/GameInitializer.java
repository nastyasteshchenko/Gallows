package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.model.action.ModelAction;
import backend.academy.view.action.ViewAction;

public class GameInitializer {

    Controller initialize() {
        Model model = new Model();
        ModelAction modelAction = new ModelAction(model);
        Controller controller = new Controller();
        ViewAction viewAction = new ViewAction();

        viewAction.initialize(modelAction);
        modelAction.initialize(viewAction);
        controller.initialize(modelAction.startNewGameAction());

        return controller;
    }
}
