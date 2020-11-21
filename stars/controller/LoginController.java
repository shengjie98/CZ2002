package stars.controller;

import stars.boundary.*;
import stars.exceptions.InvalidAccessPeriodException;

/**
 * Manages the diffrent Authenticators
 */
public class LoginController {
    /**
     * Verifies the username and passsword of the user
     * 
     * @param username username of the user trying to log in
     * @param password password of the user trying to log in
     */
    public boolean verifyLogin(String username, String password) throws InvalidAccessPeriodException {
        Authenticator studenAuthenticator = new FlatFileStudentAuthenticator();
        Authenticator adminAuthenticator = new FlatFileAdminAuthenticator();

        boolean verification = adminAuthenticator.authenticate(username, password);
        if (verification) {
            AdminUI ui = new AdminUI();
            ui.displayMenu();
            return true;
        } else {
            verification = studenAuthenticator.authenticate(username, password);
            if (verification) {
                StudentUI ui = new StudentUI(username);
                ui.displayMenu();
                return true;
            }
        }
        return false;
    }
}
