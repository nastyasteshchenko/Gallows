package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

class Dictionary {

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder().build();
    private static final String DICTIONARY_FILE = "/dictionary.json";

    @JsonProperty("dictionary")
    private HashMap<String, HashMap<Difficulty, List<String>>> dictionary;

    private Dictionary() {
    }

    static Dictionary loadDictionary() throws IOException {
        return OBJECT_MAPPER.readValue(Dictionary.class.getResourceAsStream(DICTIONARY_FILE), Dictionary.class);
    }

    List<String> getThemes() {
        return dictionary.keySet().stream().toList();
    }

    String getRandomTheme() {
        return getThemes().get((int) (Math.random() * dictionary.size()));
    }

    String getRandomWord(String theme, Difficulty difficulty) {
        List<String> possibleWords = dictionary.get(theme).get(difficulty);
        return possibleWords.get((int) (Math.random() * possibleWords.size()));
    }
}
