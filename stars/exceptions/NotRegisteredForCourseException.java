package stars.exceptions;

/**
 * Exception when Student that we are swapping with has not registered for the
 * Course
 */
public class NotRegisteredForCourseException extends Exception {
    /**
     * Default message is thrown: "Error! Student is not registered for that
     * Course!"
     */
    public NotRegisteredForCourseException() {
        super("Error! Student is not registered for that Course!");
    }

    /**
     * Custom message is thrown: Custom message is the message passed into the
     * method
     * 
     * @param message Custom Message
     */
    public NotRegisteredForCourseException(String message) {
        super(message);
    }
}
