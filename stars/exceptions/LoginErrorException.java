package stars.exceptions;

/**
 * Exception when either Username or Password can't be found
 */
public class LoginErrorException extends Exception {
    /**
     * Default message is thrown: "Account does not exist!"
     */
    public LoginErrorException() {
        super("Account does not exist!");
    }

    /**
     * Custom message is thrown: Custom message is the message passed into the
     * method
     * 
     * @param message Custom Message
     */
    public LoginErrorException(String message) {
        super(message);
    }
}
