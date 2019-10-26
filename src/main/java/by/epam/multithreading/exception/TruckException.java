package by.epam.multithreading.exception;

public class TruckException extends Exception {
    public TruckException() {
    }

    public TruckException(String message) {
        super(message);
    }

    public TruckException(String message, Throwable cause) {
        super(message, cause);
    }

    public TruckException(Throwable cause) {
        super(cause);
    }
}
