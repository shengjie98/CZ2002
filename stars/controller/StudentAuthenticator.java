package stars.controller;

import java.time.LocalDateTime;

/**
 * Interface for student authentication, abstracts authentication method from client
 */
public interface StudentAuthenticator extends Authenticator {
    /**
     * Edit the access period of a student
     * @param username studentID of student whose access period is being edited
     * @param accessStart start of new access period
     * @param accessEnd end of new access period
     */
    public void editAccess(String username, LocalDateTime accessStart, LocalDateTime accessEnd);
    
    /**
     * Add new student login particulars to flat file
     * @param username Username of new student
     * @param password Password of new student
     * @param accessStart start of student access period
     * @param accessEnd end of student access period
     */
    public void addStudent(String username, String password, LocalDateTime accessStart, LocalDateTime accessEnd);
}
