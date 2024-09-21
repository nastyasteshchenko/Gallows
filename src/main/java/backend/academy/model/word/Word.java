package backend.academy.model.word;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Word {

    @Getter
    private int guessedLettersAmount = 0;
    private final StringBuilder stringBuilder = new StringBuilder();
    private final List<Letter> letters;

    public Word(String word) {
        letters = new ArrayList<>(word.length());
        for (int i = 0; i < word.length(); i++) {
            letters.add(new Letter(word.charAt(i)));
        }
    }

    public String getWord() {
        return letters.stream().map(Letter::letter)
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
    }

    @Override
    public String toString() {
        stringBuilder.setLength(0);
        List<Character> characters = letters.stream().map(l -> l.isGuessed() ? l.letter() : '_').toList();
        for (char c : characters) {
            stringBuilder.append(c).append(' ');
        }
        return stringBuilder.toString();
    }

    public boolean isGuessed() {
        return guessedLettersAmount == letters.size();
    }

    public boolean guessLetter(String letter) {
        letters.stream().filter(l -> letter.equalsIgnoreCase(String.valueOf(l.letter())))
            .forEach(Letter::setGuessed);
        int oldGuessedLettersAmount = guessedLettersAmount;
        guessedLettersAmount = (int) letters.stream().filter(Letter::isGuessed).count();
        return guessedLettersAmount > oldGuessedLettersAmount;
    }
}
