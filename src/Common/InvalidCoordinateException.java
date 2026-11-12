package Common;

public class InvalidCoordinateException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;
    public InvalidCoordinateException(String message) {
        super(message);
    }
}