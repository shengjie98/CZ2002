package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

public class StudentRegisteredCourses implements Serializable {
    private ArrayList<Index> waitlistedIndexArray;
    private ArrayList<Index> confirmedIndexArray;
    private Student owner;
    private int AU;

    /**
     * Constructor of the StudentRegisteredCourses
     * 
     * @param owner The owner is the Student that composes of the
     *              StudentRegisteredCourses Object
     */
    public StudentRegisteredCourses(Student owner) {
        this.owner = owner;
        waitlistedIndexArray = new ArrayList<Index>();
        confirmedIndexArray = new ArrayList<Index>();
    }

    /**
     * Gets a merged list of both the list of Indexes from confirmed courses that
     * the Student has registered for and the waitlist courses that the Student is
     * in
     * 
     * @return An ArrayList consisting of the combined list of both the Indexes
     *         under confirmed courses and waitlist courses
     */
    public ArrayList<Index> getIndexList() {
        ArrayList<Index> temp = (ArrayList<Index>) this.waitlistedIndexArray.clone();
        temp.addAll(this.confirmedIndexArray);
        return temp;
    }

    /**
     * Gets the list of Indexes from the courses that the Student is on the waitlist
     * for
     * 
     * @return An ArrayList of the Indexes under the Courses that the Student is in
     *         the waitlist for
     */
    public ArrayList<Index> getWaitlistedIndexArray() {
        return waitlistedIndexArray;
    }

    /**
     * Gets the list of Indexes from the confirmed courses that the Student has
     * registered for
     * 
     * @return An ArrayList of the Indexes from the confirmed Courses that the
     *         Student is in
     */
    public ArrayList<Index> getConfirmedIndexArray() {
        return confirmedIndexArray;
    }

    /**
     * Gets the total number of AUs from all the Courses that the Student is
     * registered for
     * 
     * @return The total AU
     */
    public int getAU() {
        return AU;
    }

    /**
     * Adds the a certain number of AUs to the total AU of the Student
     * 
     * @param au The AU to be added to the total AU
     */
    public void addAU(int au) {
        this.AU += au;
    }

    /**
     * Adds Index to the list of confirmed Indexes that the Student is registered
     * for
     * 
     * @param index The confirmed Index that is to be added into the list of
     *              Confirmed Indexes
     */
    public void addToConfirmedIndexArray(Index index) {
        confirmedIndexArray.add(index);
    }

    /**
     * Adds Index to the list of waitlisted
     * 
     * @param index
     */
    public void addToWaitlistedIndexArray(Index index) {
        waitlistedIndexArray.add(index);
    }

    public void removeFromConfirmedIndexArray(Index index) {
        confirmedIndexArray.remove(index);
    }

    public void removeFromWaitlistedIndexArray(Index index) {
        waitlistedIndexArray.remove(index);
    }

    public void moveToConfirmed(Index index) {
        confirmedIndexArray.add(index);
        waitlistedIndexArray.remove(index);
    }

    public Student getOwner() {
        return owner;
    }

    public boolean addIndex(Index index) {
        IndexStudentAdder indexStudentAdder = new IndexStudentAdder();
        boolean success = indexStudentAdder.addStudent(index, this);
        return success;
    }

    public boolean dropIndex(Index index) {
        IndexStudentDropper indexStudentDropper = new IndexStudentDropper();
        boolean success = indexStudentDropper.dropStudent(index, this);
        return success;
    }

    public boolean changeIndex(Index oldIndex, Index newIndex) {
        TimetableClashChecker clashChecker = new TimetableClashChecker();
        if (oldIndex.getIndexNumber() == newIndex.getIndexNumber()) {
            return false;
        }
        boolean allowed = clashChecker.checkClash(this, newIndex, oldIndex);
        // System.out.println("checkclash result: ");
        // System.out.println(allowed);
        if (allowed) {
            IndexStudentAdder adder = new IndexStudentAdder();
            IndexStudentDropper dropper = new IndexStudentDropper();
            allowed = dropper.dropStudent(oldIndex, this);
            // System.out.println("drop result: ");
            // System.out.println(allowed);
            allowed = adder.addStudent(newIndex, this);
            // System.out.println("add result: ");
            // System.out.println(allowed);
        }
        return allowed;
    }

    public void swopPlaces(Index friendIndex, Student friend) {
        IndexStudentSwopper swopper = new IndexStudentSwopper();
        swopper.swopStudent(friendIndex, friend, this.owner);
    }
}
