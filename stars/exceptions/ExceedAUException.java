package stars.exceptions;

public class ExceedAUException extends Exception{
    public ExceedAUException() {
        super("Error! Max AU reached!");
    } 

    public ExceedAUException(String message) {
        super(message);
    }
}
