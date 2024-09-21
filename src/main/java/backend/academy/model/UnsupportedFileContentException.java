package backend.academy.model;

class UnsupportedFileContentException extends Exception {

    UnsupportedFileContentException(String message) {
        super(message);
    }

    static UnsupportedFileContentException unsupportedFileContentException(String word) {
        return new UnsupportedFileContentException("Unsupported file content: " + word);
    }
}
