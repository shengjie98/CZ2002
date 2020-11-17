
package stars.entity;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Stores a timeslot that corresponds to Tutorial, Lecture or Lab session
 */
public class Timing implements Serializable {

    /**
     * Provides the group of constants for the Type of Session (Tutorial, Lab,
     * Lecture) that the Timing corresponds to
     */
    public static enum Type {
        TUT, LAB, LEC
    }

    /**
     * Provides the group of constants for the Day of that Timing (MON, TUE, WED,
     * THU, FRI)
     */
    public static enum Day {
        MON, TUE, WED, THU, FRI
    }

    private Day day;
    private Type type;
    private LocalTime start;
    private LocalTime end;

    /**
     * Constructor for Timing
     * 
     * @param day   The day that the Timing is on
     * @param type  The type of session that the Timing corresponds to
     * @param start The start time of the Timing
     * @param end   The end time of the Timing
     */
    public Timing(Day day, Type type, LocalTime start, LocalTime end) {
        this.day = day;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    /**
     * method that checks if 2 Timings overlap
     * 
     * @param otherTiming The other Timing that the current Timing is being compared
     *                    to
     * @return true if there is no overlap
     */
    public boolean checkOverlap(Timing otherTiming) {
        if (this.day != otherTiming.day) {
            return true;
        }
        if ((this.start.isBefore(otherTiming.getStart()) && this.end.isBefore(otherTiming.getStart()))
                || (this.start.isAfter(otherTiming.getEnd()) && this.end.isAfter(otherTiming.getEnd()))) {
            return true;
        }
        return false;
    }

    /**
     * Get start time of the Timing
     * 
     * @return Start time of the Timing
     */
    public LocalTime getStart() {
        return start;
    }

    /**
     * Sets the start time of the Timing
     * 
     * @param start The start time of the Timing
     */
    public void setStart(LocalTime start) {
        this.start = start;
    }

    /**
     * Gets the end time of the Timing
     * 
     * @return The end time of the Timing
     */
    public LocalTime getEnd() {
        return end;
    }

    /**
     * Sets the end time of the Timing
     * 
     * @param end The end time of the Timing
     */
    public void setEnd(LocalTime end) {
        this.end = end;
    }

    /**
     * Gets the Day of the week (MON/TUE/WED/THU/FRI) that that Timing is on
     * 
     * @return The Day of the week (MON/TUE/WED/THU/FRI) that the Timing is on
     */
    public Timing.Day getDay() {
        return day;
    }

    /**
     * Gets the Type of Session (LEC/TUT/LAB) that the Timing belongs to
     * 
     * @return The Type of Session (LEC/TUT/LAB) that the Timing belongs to
     */
    public Timing.Type getType() {
        return type;
    }
}
