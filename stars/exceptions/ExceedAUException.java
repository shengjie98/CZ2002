package stars.exceptions;

/**
 * Exception thrown when student tries to add more AUs than allowed
 */
public class ExceedAUException extends Exception {
    public ExceedAUException() {
        super("Error! Max AU reached!");
    }

    public ExceedAUException(String message) {
        super(message);
    }
}
