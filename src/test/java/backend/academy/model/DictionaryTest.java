package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DictionaryTest {

    @Test
    void gettingRandomWordTest() throws UnsupportedFileContentException, IOException {
        Dictionary dictionary = Dictionary.loadDictionary();

        String theme = dictionary.getRandomTheme();
        Difficulty difficulty = Difficulty.getRandomDifficulty();
        String word = dictionary.getRandomWord(theme, difficulty);

        ObjectMapper mapper = JsonMapper.builder().build();
        TmpDictionary tmpDictionary =
            mapper.readValue(ClassLoader.getSystemResource("dictionary.json"), TmpDictionary.class);

        assertTrue(tmpDictionary.dictionary().get(theme).get(difficulty).contains(word));
    }

    @Getter
    private static class TmpDictionary {

        @JsonProperty("dictionary")
        private HashMap<String, HashMap<Difficulty, List<String>>> dictionary;
    }
}
