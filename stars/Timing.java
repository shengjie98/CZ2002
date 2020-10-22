
package stars;
// private enum day {
//     MON, TUES, WED, THUR, FRI
// }

// private enum type {
//     TUT, LAB, LEC
// }
public class Timing {

    public Timing(String day, String type, int start, int end){
        this.day = day;
        this.type = type;
        this.start = start;
        this.end = end;
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
