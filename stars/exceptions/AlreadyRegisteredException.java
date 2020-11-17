package stars.exceptions;

/**
 * Exception thrown when student tries to join a course or index that he is
 * already in
 */
public class AlreadyRegisteredException extends Exception {
    /**
     * Default message is thrown: "Error! Already Registered for course!"
     */
    public AlreadyRegisteredException() {
        super("Error! Already Registered for course!");
    }

    /**
     * Custom message is thrown: Custom message is the message passed into the
     * method
     * 
     * @param message Custom Message
     */
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
