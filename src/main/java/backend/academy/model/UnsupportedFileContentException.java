package backend.academy.model;

public class UnsupportedFileContentException extends Exception {

    public UnsupportedFileContentException(String word) {
        super("Unsupported file content: " + word);
    }
}
