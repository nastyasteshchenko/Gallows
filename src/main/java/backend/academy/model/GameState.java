package backend.academy.model;

import backend.academy.model.word.Word;

record GameState(Difficulty difficulty, String theme, Word word) {
}
