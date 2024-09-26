package backend.academy.model.word;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WordTest {

    @Test
    void getWordTest() {
        Word word = new Word("test");

        assertEquals("test", word.getWord());
    }

    @Test
    void isGuessedTest() {
        String str = "test";
        Word word = new Word(str);

        assertFalse(word.isGuessed());

        word.guessLetter("t");
        assertFalse(word.isGuessed());

        word.guessLetter("e");
        word.guessLetter("s");
        assertTrue(word.isGuessed());
    }

    @Test
    void guessLetterTest() {
        String str = "test";
        Word word = new Word(str);

        word.guessLetter("t");
        assertEquals("t _ _ t", word.toString());
    }

    @Test
    void guessedLettersAmountTest() {
        String str = "test";
        Word word = new Word(str);

        word.guessLetter("t");
        assertEquals(2, word.guessedLettersAmount());
    }
}
