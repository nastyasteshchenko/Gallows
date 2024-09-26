package backend.academy;

import backend.academy.controller.Controller;
import backend.academy.model.Model;
import backend.academy.view.View;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameInitializer {

    Controller initialize() {
        Model model = new Model();
        Controller controller = new Controller();
        View view = new View();

        model.initialize(view);
        view.initialize(model);
        controller.initialize(model);

        return controller;
    }
}
