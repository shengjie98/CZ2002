package stars.exceptions;

public class AlreadyRegisteredException extends Exception{
    public AlreadyRegisteredException() {
        super("Error! Already Registered for course!");
    } 

    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
