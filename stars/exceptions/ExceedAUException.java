package stars.exceptions;

/**
 * Exception thrown when student tries to add more AUs than allowed
 */
public class ExceedAUException extends Exception {
    /**
     * Default message is thrown: "Error! Max AU reached!"
     */
    public ExceedAUException() {
        super("Error! Max AU reached!");
    }

    /**
     * Custom message is thrown: Custom message is the message passed into the
     * method
     * 
     * @param message Custom Message
     */
    public ExceedAUException(String message) {
        super(message);
    }
}
