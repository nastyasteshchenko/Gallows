package backend.academy.model.word;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

public class Word {

    @Getter
    private int guessedLettersAmount = 0;
    private final List<Letter> letters = new ArrayList<>();

    public Word(String word) {
        for (int i = 0; i < word.length(); i++) {
            letters.add(new Letter(word.charAt(i)));
        }
    }

    public List<Character> getLetters() {
        return letters.stream().map(l -> l.isGuessed() ? l.letter() : '_').toList();
    }

    public boolean isGuessed() {
        return guessedLettersAmount == letters.size();
    }

    public boolean guessLetter(String letter) {
        letters.stream().filter(l -> letter.equalsIgnoreCase(String.valueOf(l.letter())))
            .forEach(Letter::setGuessed);
        int oldGuessedLettersAmount = guessedLettersAmount;
        guessedLettersAmount = (int) letters.stream().filter(Letter::isGuessed).count();
        return guessedLettersAmount != oldGuessedLettersAmount;
    }
}
