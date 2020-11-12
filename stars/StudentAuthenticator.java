package stars;

import java.time.LocalDateTime;

public interface StudentAuthenticator extends Authenticator {
    public void editAccess(String username, LocalDateTime accessStart, LocalDateTime accessEnd);
    public void addStudent(String username, String password, LocalDateTime accessStart, LocalDateTime accessEnd);
}
