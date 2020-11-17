package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.boundary.*;

/**
 * Checks if there are any clashes between the Indexes in the
 * StudentRegisteredCourses and the new Index
 */
public class TimetableClashChecker {
    /**
     * Checks if there is a clash between the Timings of the Indexes in the
     * StudentRegisteredCourses and Timings of the new Index that is to be added to
     * the StudentRegisteredCourses
     * 
     * @param studentRegisteredCourses The StudentRegisteredCourses of the Student
     * @param newIndex                 The new Index to be added into the
     *                                 StudentRegisteredCourses
     * @return Boolean that indicates true is there is no clash and false if there
     *         is a clash
     */
    public boolean checkClash(StudentRegisteredCourses studentRegisteredCourses, Index newIndex) {
        // check to add
        ArrayList<Index> indexArray = studentRegisteredCourses.getWaitlistedIndexArray();
        ArrayList<Timing> timings;
        for (Index curIndex : indexArray) {
            timings = curIndex.getTimings();
            for (Timing curTiming : timings) {
                for (Timing newTiming : newIndex.getTimings()) {
                    if (!curTiming.checkOverlap(newTiming)) {
                        return false;
                    }
                }
            }
        }
        indexArray = studentRegisteredCourses.getConfirmedIndexArray();
        for (Index curIndex : indexArray) {
            timings = curIndex.getTimings();
            for (Timing curTiming : timings) {
                for (Timing newTiming : newIndex.getTimings()) {
                    if (!curTiming.checkOverlap(newTiming)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if there is a clash between the StudentRegisteredCourses of the
     * Student for the cases where an Index is to be swopped for another Index of
     * the same Course
     * 
     * @param studentRegisteredCourses The StudentRegisteredCourse of the Student
     * @param newIndex                 The new Index that the Student wants to swop
     *                                 to
     * @param oldIndex                 The current Index of the Student
     * @return boolean that indicates true if there is no clash and false if there
     *         is a clash
     */
    public boolean checkClash(StudentRegisteredCourses studentRegisteredCourses, Index newIndex, Index oldIndex) {
        // check to change
        ArrayList<Index> indexArray = studentRegisteredCourses.getWaitlistedIndexArray();
        ArrayList<Timing> timings;
        for (Index curIndex : indexArray) {
            if (curIndex.getIndexNumber() == oldIndex.getIndexNumber()) {
                continue;
            }
            timings = curIndex.getTimings();
            for (Timing curTiming : timings) {
                for (Timing newTiming : newIndex.getTimings()) {
                    if (!curTiming.checkOverlap(newTiming)) {
                        return false;
                    }
                }
            }
        }
        indexArray = studentRegisteredCourses.getConfirmedIndexArray();
        for (Index curIndex : indexArray) {
            if (curIndex.getIndexNumber() == oldIndex.getIndexNumber()) {
                continue;
            }
            timings = curIndex.getTimings();
            for (Timing curTiming : timings) {
                for (Timing newTiming : newIndex.getTimings()) {
                    if (!curTiming.checkOverlap(newTiming)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
