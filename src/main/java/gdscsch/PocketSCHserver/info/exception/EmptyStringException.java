package gdscsch.PocketSCHserver.info.exception;

public class EmptyStringException extends RuntimeException {

    EmptyStringException() {}

    public EmptyStringException(String message) {
        super(message);
    }
}
