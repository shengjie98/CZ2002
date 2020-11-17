package stars.entity;

import java.io.Serializable;

/**
 * This interface provides a layer of abstraction for Student to call the
 * sendNotification method without having to know whether an EmailSerivce is
 * being used or an SMSService, etc is being used so that the Notification
 * mechanism is extensible and the Notification methods are interchageable.
 */
public interface NotificationInterface extends Serializable {
    /**
     * Sends the Notification to the Student through Email, SMS, etc depending on
     * the Subclass Implementation of this method
     * 
     * @param index The Index in which the Student has been shifted from waitlist to
     *              confirmed list
     */
    public void sendNotification(Index index);
}
