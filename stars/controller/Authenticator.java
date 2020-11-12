package stars.controller;

import stars.entity.*;
import stars.boundary.*;

public interface Authenticator {
    public boolean authenticate(String username, String password);
}
