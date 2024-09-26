package backend.academy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Model {
    private GameState currentGameState;
    private Dictionary dictionary;
}
