package backend.academy.model;

import backend.academy.model.word.Word;
import backend.academy.view.GameInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void isWinTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);

        gameState.guessLetter("t");
        assertFalse(gameState.isWin());

        gameState.guessLetter("E");
        assertFalse(gameState.isWin());

        gameState.guessLetter("s");
        assertTrue(gameState.isWin());
    }

    @Test
    void isLooseTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.HARD, "test", word);

        gameState.guessLetter("t");
        assertFalse(gameState.isLoose());

        gameState.guessLetter("M");
        assertFalse(gameState.isLoose());

        gameState.guessLetter("s");
        assertFalse(gameState.isLoose());

        gameState.guessLetter("B");
        assertFalse(gameState.isLoose());

        gameState.guessLetter("v");
        assertFalse(gameState.isLoose());

        gameState.guessLetter("a");
        assertTrue(gameState.isLoose());
    }

    @Test
    void isAlreadyUsedTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);

        gameState.guessLetter("t");
        assertTrue(gameState.isAlreadyUsed("t"));

        assertFalse(gameState.isAlreadyUsed("E"));
    }

    @Test
    void isInAlphabetTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);

        assertTrue(gameState.isInAlphabet("t"));
        assertTrue(gameState.isInAlphabet("E"));
        assertFalse(gameState.isInAlphabet("-"));
        assertFalse(gameState.isInAlphabet("ф"));
    }

    @Test
    void guessLetterTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);

        gameState.guessLetter("t");
        assertEquals("t _ _ t ", gameState.word().toString());
    }

    @Test
    void wordTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);

        assertEquals(word, gameState.word());
    }

    @Test
    void gameInfoTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);
        GameInfo gameInfo = gameState.getGameInfo();
        assertEquals("_ _ _ _ ", gameInfo.wordLetters());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.attemptsLeft());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.totalAttempts());
        assertEquals("test", gameInfo.theme());
        assertEquals("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z ", gameInfo.alphabet());

        gameState.guessLetter("t");
        gameInfo = gameState.getGameInfo();
        assertEquals("t _ _ t ", gameInfo.wordLetters());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.attemptsLeft());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.totalAttempts());
        assertEquals("test", gameInfo.theme());
        assertEquals("A B C D E F G H I J K L M N O P Q R S U V W X Y Z ", gameInfo.alphabet());

        gameState.guessLetter("M");
        gameInfo = gameState.getGameInfo();
        assertEquals("t _ _ t ", gameInfo.wordLetters());
        assertEquals(Difficulty.EASY.attempts() - 1, gameInfo.attemptsLeft());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.totalAttempts());
        assertEquals("test", gameInfo.theme());
        assertEquals("A B C D E F G H I J K L N O P Q R S U V W X Y Z ", gameInfo.alphabet());
    }
}
