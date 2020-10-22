public class Timing {
    private enum day {
        MON, TUES, WED, THUR, FRI
    }

    private enum type {
        TUT, LAB, LEC
    }

    private int start;
    private int end;

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
