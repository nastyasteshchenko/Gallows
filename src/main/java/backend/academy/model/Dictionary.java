package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;

final class Dictionary {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder().build();
    private static final String DICTIONARY_FILE = "/dictionary.json";

    private final SecureRandom random = new SecureRandom();
    @JsonProperty("dictionary")
    private HashMap<String, HashMap<Difficulty, List<String>>> dictionary;

    private Dictionary() {
    }

    static Dictionary loadDictionary() throws IOException, UnsupportedFileContentException {
        Dictionary dictionary =
            OBJECT_MAPPER.readValue(Dictionary.class.getResource(DICTIONARY_FILE), Dictionary.class);
        dictionary.checkDictionary();
        return dictionary;
    }

    List<String> getThemes() {
        return dictionary.keySet().stream().toList();
    }

    String getRandomTheme() {
        return getThemes().get(random.nextInt(dictionary.size()));
    }

    String getRandomWord(String theme, Difficulty difficulty) {
        List<String> possibleWords = dictionary.get(theme).get(difficulty);
        return possibleWords.get(random.nextInt(possibleWords.size()));
    }

    private void checkDictionary() throws UnsupportedFileContentException {
        List<String> allWords = dictionary.values().stream()
            .flatMap(map -> map.values().stream()).flatMap(List::stream).toList();
        for (String word : allWords) {
            if (!word.matches("[a-zA-Z]+")) {
                throw UnsupportedFileContentException.unsupportedFileContentException(word);
            }
        }
    }
}
