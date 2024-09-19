package backend.academy.view;

import lombok.Getter;

@Getter
public enum GallowsImage {

    NO_GALLOWS("""






        """),

    PART_OF_GALLOWS("""
           |
           |
           |
           |
           |
           -
        """),

    GALLOWS("""
           --------
           |      |
           |
           |
           |
           |
           -
        """),

    GALLOWS_WITH_HEAD("""
           --------
           |      |
           |      O
           |
           |
           |
           -
        """),

    GALLOWS_WITH_HEAD_AND_TORSO("""
           --------
           |      |
           |      O
           |      |
           |      |
           |
           -
        """),

    GALLOWS_WITH_HEAD_AND_LEFT_ARM("""
           --------
           |      |
           |      O
           |     \\|
           |      |
           |
           -
        """),

    GALLOWS_WITH_HEAD_AND_BOTH_ARMS("""
           --------
           |      |
           |      O
           |     \\|/
           |      |
           |
           -
        """),

    GALLOWS_WITH_HEAD_AND_BOTH_ARMS_AND_RIGHT_LEG("""
           --------
           |      |
           |      O
           |     \\|/
           |      |
           |     /
           -
        """),

    GALLOWS_WITH_HEAD_AND_BOTH_ARMS_AND_BOTH_LEGS("""
           --------
           |      |
           |      O
           |     \\|/
           |      |
           |     / \\
           -
        """);

    private final String image;

    GallowsImage(String image) {
        this.image = image;
    }

    public static GallowsImage getByMistakesAmountAndDifficulty(int mistakesAmount, int attempts) {
        int imageIndex = mistakesAmount * (values().length + 1) / (attempts + 1);
        return values()[imageIndex];
    }
}
