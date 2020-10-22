
package stars;
import java.time.LocalTime;

public class Timing {
    static enum Type {
        TUT,
        LAB, 
        LEC
    }

    static enum Day {
        MON, 
        TUE,
        WED,
        THU, 
        FRI
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
     * @param otherTiming
     * @return true if there is no overlap
     */
    public boolean checkOverlap(Timing otherTiming) {
        if (this.day != otherTiming.day) {
            return true;
        } 
        if ((this.start.isBefore(otherTiming.getStart())&&this.start.isBefore(otherTiming.getEnd()))||(this.start.isAfter(otherTiming.getStart())&&this.start.isAfter(otherTiming.getEnd()))) {
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
}
