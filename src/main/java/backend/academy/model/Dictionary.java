package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Dictionary {

    private static final Logger LOGGER = LogManager.getLogger(Dictionary.class);

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder().build();
    private static final String DICTIONARY_FILE = "/dictionary.json";
    public static final String GAME_WORDS_REGEX = "[a-zA-Z]+";

    private final SecureRandom random = new SecureRandom();
    @JsonProperty("dictionary")
    private HashMap<String, HashMap<Difficulty, List<String>>> dictionary;

    private Dictionary() {
    }

    public static Dictionary loadDictionary() {
        try {
            Dictionary dictionary =
                OBJECT_MAPPER.readValue(Dictionary.class.getResource(DICTIONARY_FILE), Dictionary.class);
            dictionary.checkDictionary();
            return dictionary;
        } catch (IOException | UnsupportedFileContentException e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List<String> getThemes() {
        return dictionary.keySet()
            .stream()
            .toList();
    }

    public String getRandomTheme() {
        return getThemes().get(random.nextInt(dictionary.size()));
    }

    public String getRandomWord(String theme, Difficulty difficulty) {
        List<String> possibleWords = dictionary.get(theme).get(difficulty);
        return possibleWords.get(random.nextInt(possibleWords.size()));
    }

    private void checkDictionary() throws UnsupportedFileContentException {
        List<String> allWords = dictionary.values().stream()
            .flatMap(map -> map.values().stream()).flatMap(List::stream).toList();
        for (String word : allWords) {
            if (!word.matches(GAME_WORDS_REGEX)) {
                throw new UnsupportedFileContentException(word);
            }
        }
    }
}
