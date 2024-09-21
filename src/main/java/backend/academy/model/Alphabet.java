package backend.academy.model;

import java.util.LinkedList;
import java.util.List;

class Alphabet {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final List<Character> freeLetters = new LinkedList<>();
    private final StringBuilder stringBuilder = new StringBuilder();

    Alphabet() {
        for (int i = 0; i < ALPHABET.length(); i++) {
            freeLetters.add(ALPHABET.charAt(i));
        }
    }

    boolean isInAlphabet(String letter) {
        return ALPHABET.contains(letter.toUpperCase());
    }

    boolean isAlreadyUsed(String letter) {
        return !freeLetters.contains(letter.toUpperCase().charAt(0));
    }

    void makeLetterAsUsed(String letter) {
        freeLetters.remove((Character) letter.toUpperCase().charAt(0));
    }

    @Override
    public String toString() {
        stringBuilder.setLength(0);
        for (char freeLetter : freeLetters) {
            stringBuilder.append(freeLetter).append(" ");
        }
        stringBuilder.append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
