package stars.controller;

/**
 * Interface for authentication, abstracts authentication method from client
 */
public interface Authenticator {
    public boolean authenticate(String username, String password);
}
