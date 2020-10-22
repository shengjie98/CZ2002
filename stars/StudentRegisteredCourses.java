package stars;

import java.util.ArrayList;
import java.time.LocalTime;

public class StudentRegisteredCourses{
    ArrayList<Index> waitlistedIndexArray;
    ArrayList<Index> confirmedIndexArray;
    Student owner; 

    public StudentRegisteredCourses(Student owner) {
        this.owner = owner;
        waitlistedIndexArray = new ArrayList<Index>();
        confirmedIndexArray = new ArrayList<Index>();
    }

    public boolean addIndex(Index index) {
        ArrayList<Timing> timings;
        boolean confirmed;
        for (Index curIndex: waitlistedIndexArray) {
            timings = curIndex.getTimings();
            for (Timing curTiming: timings) {
                for (Timing newTiming: index.getTimings()){
                    if (!curTiming.checkOverlap(newTiming)){
                        return false;
                    }
                }
            }
        }
        confirmed = index.addStudent(this.owner);
        if (confirmed) {
            this.confirmedIndexArray.add(index);
        } else {
            this.waitlistedIndexArray.add(index);
        }
        return true;
    }

    public boolean dropIndex(Index index) {
        boolean confirmed;
        confirmed = index.dropStudent(this.owner);
        if (confirmed) {
            this.confirmedIndexArray.remove(index);
        } else {
            this.waitlistedIndexArray.remove(index);
        }
        return true;

    }

    public boolean changeIndex(Index oldIndex, Index newIndex) {

    }

    public ArrayList<Index> getIndexList() {

    }
}
