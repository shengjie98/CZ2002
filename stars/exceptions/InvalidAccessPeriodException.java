package stars.exceptions;

/**
 * Exception when the Student tries to access the account before the start of
 * the access period or after the end of the access period
 */
public class InvalidAccessPeriodException extends Exception {
    /**
     * Default message is thrown: "Error! Max AU reached!"
     */
    public InvalidAccessPeriodException() {
        super("Error! Invalid Access Period!");
    }

    /**
     * Custom message is thrown: Custom message is the message passed into the
     * method
     * 
     * @param message Custom Message
     */
    public InvalidAccessPeriodException(String message) {
        super(message);
    }
}
