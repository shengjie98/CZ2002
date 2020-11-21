package stars.controller;

import stars.exceptions.InvalidAccessPeriodException;

/**
 * Interface for authentication, abstracts authentication method from client
 */
public interface Authenticator {
    /**
     * Authenticates username and password by user
     * 
     * @param username Username to be authenticated
     * @param password Password to be authenticated
     * @return true if authentication is successful, false if incorrect username and
     *         password is input
     */
    public boolean authenticate(String username, String password) throws InvalidAccessPeriodException;
}
