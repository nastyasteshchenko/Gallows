package backend.academy.model;

import backend.academy.model.action.GuessLetterAction;
import backend.academy.model.word.Word;
import backend.academy.view.GameInfo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private final Model model = new Model();

    @Test
    void isWinTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);
        model.currentGameState(gameState);

        GuessLetterAction guessLetterAction = new GuessLetterAction(model);

        guessLetterAction.accept("t");
        assertFalse(gameState.isWin());

        guessLetterAction.accept("E");
        assertFalse(gameState.isWin());

        guessLetterAction.accept("s");
        assertTrue(gameState.isWin());
    }

    @Test
    void isLooseTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.HARD, "test", word);
        model.currentGameState(gameState);

        GuessLetterAction guessLetterAction = new GuessLetterAction(model);

        guessLetterAction.accept("t");
        assertFalse(gameState.isLoose());

        guessLetterAction.accept("M");
        assertFalse(gameState.isLoose());

        guessLetterAction.accept("s");
        assertFalse(gameState.isLoose());

        guessLetterAction.accept("B");
        assertFalse(gameState.isLoose());

        guessLetterAction.accept("v");
        assertFalse(gameState.isLoose());

        guessLetterAction.accept("a");
        assertTrue(gameState.isLoose());
    }

    @Test
    void isAlreadyUsedTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);
        model.currentGameState(gameState);

        GuessLetterAction guessLetterAction = new GuessLetterAction(model);

        guessLetterAction.accept("t");
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
        model.currentGameState(gameState);

        GuessLetterAction guessLetterAction = new GuessLetterAction(model);

        guessLetterAction.accept("t");
        assertEquals("t _ _ t", gameState.word().toString());
    }

    @Test
    void wordTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);
        model.currentGameState(gameState);

        GuessLetterAction guessLetterAction = new GuessLetterAction(model);

        assertEquals(word, gameState.word());
    }

    @Test
    void gameInfoTest() {
        Word word = new Word("test");
        GameState gameState = new GameState(Difficulty.EASY, "test", word);
        GameInfo gameInfo = gameState.getGameInfo();
        assertEquals("_ _ _ _", gameInfo.wordLetters());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.attemptsLeft());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.totalAttempts());
        assertEquals("test", gameInfo.theme());
        assertEquals("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z", gameInfo.alphabet());

        model.currentGameState(gameState);
        GuessLetterAction guessLetterAction = new GuessLetterAction(model);

        guessLetterAction.accept("t");
        gameInfo = gameState.getGameInfo();
        assertEquals("t _ _ t", gameInfo.wordLetters());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.attemptsLeft());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.totalAttempts());
        assertEquals("test", gameInfo.theme());
        assertEquals("A B C D E F G H I J K L M N O P Q R S U V W X Y Z", gameInfo.alphabet());

        guessLetterAction.accept("M");
        gameInfo = gameState.getGameInfo();
        assertEquals("t _ _ t", gameInfo.wordLetters());
        assertEquals(Difficulty.EASY.attempts() - 1, gameInfo.attemptsLeft());
        assertEquals(Difficulty.EASY.attempts(), gameInfo.totalAttempts());
        assertEquals("test", gameInfo.theme());
        assertEquals("A B C D E F G H I J K L N O P Q R S U V W X Y Z", gameInfo.alphabet());
    }
}
