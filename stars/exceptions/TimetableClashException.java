package stars.exceptions;

/**
 * Exception when there is a timetable clash
 */
public class TimetableClashException extends Exception {
    public TimetableClashException() {
        super("Error! There is a timetable clash!");
    } 

    public TimetableClashException(String message) {
        super(message);
    }
}
