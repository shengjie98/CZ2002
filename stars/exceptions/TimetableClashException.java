package stars.exceptions;

/**
 * Exception when there is a timetable clash
 */
public class TimetableClashException extends Exception {

    /**
     * Default message is thrown: "Error! There is a timetable clash!"
     */
    public TimetableClashException() {
        super("Error! There is a timetable clash!");
    }

    /**
     * Custom message is thrown: Custom message is the message passed into the
     * method
     * 
     * @param message Custom Message
     */
    public TimetableClashException(String message) {
        super(message);
    }
}
