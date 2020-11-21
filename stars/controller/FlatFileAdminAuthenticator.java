package stars.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import stars.exceptions.LoginErrorException;

/**
 * Concrete class for admin authentication
 */
public class FlatFileAdminAuthenticator implements Authenticator {
    private final String ADMIN_ACCOUNT_FILE = "stars/adminAccount.txt";

    /**
     * Authenticates username and password by user with username and hashed password
     * stored in flat file
     * 
     * @param username Username to be authenticated
     * @param password Password to be authenticated
     * @return true if authentication is successful, false if incorrect username and
     *         password is input
     */
    public boolean authenticate(String username, String password) throws LoginErrorException {
        int hashedPassword = password.hashCode();
        String adminUsername;
        int adminPassword;
        try {
            Scanner admin = new Scanner(new File(ADMIN_ACCOUNT_FILE));
            adminUsername = admin.nextLine();
            adminPassword = admin.nextInt();
            if (username.equals(adminUsername)) {
                if (hashedPassword == adminPassword) {
                    return true;
                }
                throw new LoginErrorException("Wrong password!");
            }
        } catch (FileNotFoundException e) {
            System.out.print("file not file error\n");
            return false;
        }
        return false;
    }
}
