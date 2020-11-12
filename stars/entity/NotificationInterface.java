package stars.entity;

import java.io.Serializable;

import stars.boundary.*;
import stars.controller.*;

public interface NotificationInterface extends Serializable{
    public void sendNotification(Index index);
}
