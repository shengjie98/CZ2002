package stars.controller;

import java.time.LocalDateTime;
import stars.entity.*;
import stars.boundary.*;

public interface StudentAuthenticator extends Authenticator {
    public void editAccess(String username, LocalDateTime accessStart, LocalDateTime accessEnd);
    public void addStudent(String username, String password, LocalDateTime accessStart, LocalDateTime accessEnd);
}
