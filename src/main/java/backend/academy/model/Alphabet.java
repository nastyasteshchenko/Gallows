package backend.academy.model;

import java.util.LinkedList;
import java.util.List;

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
        StringBuilder stringBuilder = new StringBuilder();
        for (char freeLetter : freeLetters) {
            stringBuilder.append(freeLetter).append(' ');
        }
        return stringBuilder.toString();
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
