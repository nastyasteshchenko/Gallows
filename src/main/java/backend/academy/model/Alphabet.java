package backend.academy.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Alphabet {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final List<Character> freeLetters = new LinkedList<>();

    public Alphabet() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            freeLetters.add(ALPHABET.charAt(i));
        }
    }

    @Override
    public String toString() {
        return freeLetters.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(" "));
    }

    public boolean isInAlphabet(String letter) {
        return ALPHABET.contains(letter.toUpperCase());
    }

    public boolean isAlreadyUsed(String letter) {
        return !freeLetters.contains(letter.toUpperCase().charAt(0));
    }

    public void makeLetterAsUsed(String letter) {
        freeLetters.remove((Character) letter.toUpperCase().charAt(0));
    }
}
