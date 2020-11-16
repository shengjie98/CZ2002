package stars.boundary;

import stars.entity.*;
import stars.controller.*;
import java.time.LocalTime;


public class TimetableDisplayer {   
    private Student student;
    private final int WIDTH = 24;
    private final int HEIGHT = 1;

    public TimetableDisplayer(Student student){
        this.student = student;
    }

    public void displayTimetable() {
        String courseID, indexID, display;
        int i, j, k, col, row;
        String[][] arr = new String[48][5];
        for (i = 0; i<48; i++) {
            for (j=0; j<5; j++) {
                arr[i][j] = " ".repeat(WIDTH) ;
            }
        }
        
        for (Index index: student.getConfirmedIndex()) {
            courseID = index.getCourse().getCourseID();
            indexID = Integer.toString(index.getIndexNumber());
            for (Timing time: index.getTimings()) {
                display = courseID + " " + indexID + " " + time.getType().toString();
                switch (time.getDay()) {
                    case MON:
                        col = 0;    
                        break;
                    case TUE:
                        col = 1;    
                        break;
                    case WED:
                        col = 2;    
                        break;
                    case THU:
                        col = 3;    
                        break;
                    case FRI:
                        col = 4;    
                        break;
                    default:
                        col = 0;
                        break;
                }

                LocalTime start, end;
                start = time.getStart();
                end = time.getEnd();
                int minutes = (end.getHour() - start.getHour())*60 + end.getMinute() - start.getMinute();
                int periods = (int) Math.round((double) minutes / 30);
                int rowStart = start.getHour() * 2;
                minutes = start.getMinute();
                if ( 15 < minutes && minutes < 45) {
                    rowStart++;
                } else if (minutes >= 45) {
                    rowStart += 2;
                }
                for (row = rowStart; row < rowStart + periods; row++) {
                    arr[row][col] = pad(display.substring(0, Math.min(WIDTH, display.length())));
                }
            }
        }

        for (Index index: student.getWaitlistedIndex()) {
            courseID = index.getCourse().getCourseID();
            indexID = Integer.toString(index.getIndexNumber());
            for (Timing time: index.getTimings()) {
                display = courseID + " " + indexID + " " + time.getType().toString() + "(Waitlist)";
                switch (time.getDay()) {
                    case MON:
                        col = 0;    
                        break;
                    case TUE:
                        col = 1;    
                        break;
                    case WED:
                        col = 2;    
                        break;
                    case THU:
                        col = 3;    
                        break;
                    case FRI:
                        col = 4;    
                        break;
                    default:
                        col = 0;
                        break;
                }

                LocalTime start, end;
                start = time.getStart();
                end = time.getEnd();
                int minutes = (end.getHour() - start.getHour())*60 + end.getMinute() - start.getMinute();
                int periods = (int) Math.round((double) minutes / 30);
                int rowStart = start.getHour() * 2;
                minutes = start.getMinute();
                if ( 15 < minutes && minutes < 45) {
                    rowStart++;
                } else if (minutes >= 45) {
                    rowStart += 2;
                }
                for (row = rowStart; row < rowStart + periods; row++) {
                    arr[row][col] = pad(display.substring(0, Math.min(WIDTH, display.length())));
                }
            }
        }

        int hourStart, minuteStart, hourEnd, minuteEnd;
        System.out.print("            || ");
        System.out.print(pad("MON") + " || ");
        System.out.print(pad("TUE") + " || ");
        System.out.print(pad("WED") + " || ");
        System.out.print(pad("THU") + " || ");
        System.out.print(pad("FRI") + " || ");
        System.out.println("");
        for (i = 0; i< 48; i++) {
            hourStart = (i/2)%24;
            minuteStart = (i%2) * 30;
            hourEnd = ((i+1)/2)%24;
            minuteEnd = ((i+1)%2) * 30;
            for (k = 0 ; k< HEIGHT; k++) {
                if (k == HEIGHT/2) {
                    System.out.printf("%02d:%02d-%02d:%02d", hourStart,  minuteStart, hourEnd, minuteEnd);
                } else {
                    System.out.print(" ".repeat(11));
                }
                System.out.print(" || ");
                for (j = 0; j< 5; j++) {
                    System.out.print(arr[i][j]);
                    System.out.print(" || ");
                }
                System.out.println("");
            }
        }
    }

    public String pad(String s) {
        if (s == null || WIDTH <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(WIDTH);
        for (int i = 0; i < (WIDTH - s.length()) / 2; i++) {
            sb.append(" ");
        }
        sb.append(s);
        while (sb.length() < WIDTH) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
