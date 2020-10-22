
package stars;

public class Timing {
    private String day;
    private String type;
    private int start;
    private int end;

    public Timing(String day, String type, int start, int end) {
        this.day = day;
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public int getStart() {
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
