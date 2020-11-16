package stars.exceptions;

/**
 * Exception thrown when student tries to join a course or index that he is already in
 */
public class AlreadyRegisteredException extends Exception{
    public AlreadyRegisteredException() {
        super("Error! Already Registered for course!");
    } 

    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
