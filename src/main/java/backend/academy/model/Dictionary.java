package backend.academy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.util.HashMap;

class Dictionary {
    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder().build();

    @JsonProperty("dictionary")
    private HashMap<String, HashMap<Difficulty, String[]>> dictionary;

    private Dictionary() {
    }

    static Dictionary loadDictionary() throws IOException {
        return OBJECT_MAPPER.readValue(Dictionary.class.getResourceAsStream("/dictionary.json"), Dictionary.class);
    }
}
