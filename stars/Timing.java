
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

    public LocalTime getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
