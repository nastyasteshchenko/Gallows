package backend.academy.model.word;

import lombok.Getter;

@Getter public class Letter {

    private final char letter;
    private boolean isGuessed;

    public Letter(char letter) {
        this.letter = letter;
    }

    public void setGuessed() {
        isGuessed = true;
    }
}
