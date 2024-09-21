package backend.academy.model.word;

import lombok.Getter;

@Getter
class Letter {

    private final char letter;
    private boolean isGuessed;

    Letter(char letter) {
        this.letter = letter;
    }

    void setGuessed() {
        isGuessed = true;
    }
}
