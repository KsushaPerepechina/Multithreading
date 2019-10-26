package by.epam.multithreading.exception;

public class InaccessibleFileException extends Exception {
    public InaccessibleFileException() {
    }

    public InaccessibleFileException(String message) {
        super(message);
    }

    public InaccessibleFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InaccessibleFileException(Throwable cause) {
        super(cause);
    }
}
