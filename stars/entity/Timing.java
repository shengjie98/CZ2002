
package stars.entity;

import java.io.Serializable;
import java.time.LocalTime;
import stars.boundary.*;
import stars.controller.*;

public class Timing implements Serializable {
    public static enum Type {
        TUT, LAB, LEC
    }

    public static enum Day {
        MON, TUE, WED, THU, FRI
    }

    private Day day;
    private Type type;
    private LocalTime start;
    private LocalTime end;

    public Timing(Day day, Type type, LocalTime start, LocalTime end) {
        this.day = day;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    /**
     * method that checks if 2 methods overlap
     * 
     * @param otherTiming
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public Timing.Day getDay() {
        return day;
    }

	public Timing.Type getType() {
		return type;
	}
}
