package expections;

public class OutOfRangeException extends IllegalArgumentException {
    public OutOfRangeException(final String message) {
        super(message);
    }
}
