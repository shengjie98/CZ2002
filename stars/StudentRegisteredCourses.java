package stars;

import java.util.ArrayList;

public class StudentRegisteredCourses{
    ArrayList<Index> waitlistedIndexArray;
    ArrayList<Index> confirmedIndexArray;
    Student owner; 

    public ArrayList<Index> getIndexList() {
        ArrayList<Index> temp = (ArrayList<Index>)this.waitlistedIndexArray.clone();
        temp.addAll(this.confirmedIndexArray);
        return temp;
    }
    
    public ArrayList<Index> getWaitlistedIndexArray() {
        return waitlistedIndexArray;
    }
    
    public ArrayList<Index> getConfirmedIndexArray() {
        return confirmedIndexArray;
    }

    

    public StudentRegisteredCourses(Student owner) {
        this.owner = owner;
        waitlistedIndexArray = new ArrayList<Index>();
        confirmedIndexArray = new ArrayList<Index>();
    }

    public boolean addIndex(Index index) {
        boolean confirmed, success;
        success = checkClash(index);
        if (!success) {
            return false;
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
    
    public boolean checkChangeIndex(Index oldIndex, Index newIndex){
        boolean success;
        if (this.waitlistedIndexArray.remove(oldIndex)) {
            success = checkClash(newIndex);
            this.waitlistedIndexArray.add(oldIndex);
            return success;
        } else {
            this.confirmedIndexArray.remove(oldIndex) ;
            success = checkClash(newIndex);
            this.confirmedIndexArray.add(oldIndex);
            return success;
        }

    }
    
    public boolean changeIndex(Index oldIndex, Index newIndex) {
        boolean success;
        success = checkChangeIndex(oldIndex, newIndex);
        if (success) {
            dropIndex(oldIndex);
            addIndex(newIndex);
        }
        return success;
    }

    private boolean checkClash(Index index){
        ArrayList<Timing> timings;
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
        for (Index curIndex: confirmedIndexArray) {
            timings = curIndex.getTimings();
            for (Timing curTiming: timings) {
                for (Timing newTiming: index.getTimings()){
                    if (!curTiming.checkOverlap(newTiming)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void swopPlaces(Index friendIndex, Student friend){
        boolean confirmed;
        confirmed = friendIndex.swopStudent(friend, this.owner);
        if (confirmed) {
            this.confirmedIndexArray.add(friendIndex);
        } else {
            this.waitlistedIndexArray.add(friendIndex);
        }
    }
}
