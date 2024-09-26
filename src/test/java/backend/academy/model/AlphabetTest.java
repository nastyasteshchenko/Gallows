package backend.academy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlphabetTest {

    @Test
    void isInAlphabetTest() {
        Alphabet alphabet = new Alphabet();

        assertTrue(alphabet.isInAlphabet("a"));
        assertTrue(alphabet.isInAlphabet("C"));
        assertFalse(alphabet.isInAlphabet("-"));
        assertFalse(alphabet.isInAlphabet("ф"));
    }

    @Test
    void isAlreadyUsedAndMakeLetterAsUsedTest() {
        Alphabet alphabet = new Alphabet();

        alphabet.makeLetterAsUsed("a");
        alphabet.makeLetterAsUsed("F");

        assertTrue(alphabet.isAlreadyUsed("a"));
        assertTrue(alphabet.isAlreadyUsed("F"));
        assertFalse(alphabet.isAlreadyUsed("b"));
        assertFalse(alphabet.isAlreadyUsed("G"));
    }

    @Test
    void toStringTest() {
        Alphabet alphabet = new Alphabet();

        assertEquals("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z", alphabet.toString());

        alphabet.makeLetterAsUsed("a");
        assertEquals("B C D E F G H I J K L M N O P Q R S T U V W X Y Z", alphabet.toString());

        alphabet.makeLetterAsUsed("Z");
        assertEquals("B C D E F G H I J K L M N O P Q R S T U V W X Y", alphabet.toString());
    }
}
