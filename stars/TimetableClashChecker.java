package stars;

import java.util.ArrayList;

public class TimetableClashChecker {
    public boolean checkClash(StudentRegisteredCourses studentRegisteredCourses, Index newIndex) {
        // check to add
        ArrayList<Index> indexArray = studentRegisteredCourses.getWaitlistedIndexArray();
        ArrayList<Timing> timings;
        for (Index curIndex: indexArray) {
            timings = curIndex.getTimings();
            for (Timing curTiming: timings) {
                for (Timing newTiming: newIndex.getTimings()){
                    if (!curTiming.checkOverlap(newTiming)){
                        return false;
                    }
                }
            }
        }
        indexArray = studentRegisteredCourses.getConfirmedIndexArray();
        for (Index curIndex: indexArray) {
            timings = curIndex.getTimings();
            for (Timing curTiming: timings) {
                for (Timing newTiming: newIndex.getTimings()){
                    if (!curTiming.checkOverlap(newTiming)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean checkClash(StudentRegisteredCourses studentRegisteredCourses, Index newIndex, Index oldIndex) {
        // check to change
        ArrayList<Index> indexArray = studentRegisteredCourses.getWaitlistedIndexArray();
        ArrayList<Timing> timings;
        for (Index curIndex: indexArray) {
            if (curIndex.getIndexNumber() == oldIndex.getIndexNumber()) {
                continue;
            }
            timings = curIndex.getTimings();
            for (Timing curTiming: timings) {
                for (Timing newTiming: newIndex.getTimings()){
                    if (!curTiming.checkOverlap(newTiming)){
                        return false;
                    }
                }
            }
        }
        indexArray = studentRegisteredCourses.getConfirmedIndexArray();
        for (Index curIndex: indexArray) {
            if (curIndex.getIndexNumber() == oldIndex.getIndexNumber()) {
                continue;
            }
            timings = curIndex.getTimings();
            for (Timing curTiming: timings) {
                for (Timing newTiming: newIndex.getTimings()){
                    if (!curTiming.checkOverlap(newTiming)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
