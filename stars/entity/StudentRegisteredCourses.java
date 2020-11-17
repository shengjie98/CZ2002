package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.controller.*;
import stars.exceptions.AlreadyRegisteredException;
import stars.exceptions.ExceedAUException;
import stars.exceptions.TimetableClashException;

/**
 * In charge of the handling the Courses registered by the Student
 */
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
     * Adds Index to the list of waitlisted Indexes that the Student is registered
     * for
     * 
     * @param index The waitlisted Index that is to be added into the list of
     *              Waitlisted Indexes
     */
    public void addToWaitlistedIndexArray(Index index) {
        waitlistedIndexArray.add(index);
    }

    /**
     * Removes Index from list of confirmed Indexes that the Student is registered
     * for
     * 
     * @param index The confirmed Index that is to be removed from the list of
     *              Confirmed Indexes
     */
    public void removeFromConfirmedIndexArray(Index index) {
        confirmedIndexArray.remove(index);
    }

    /**
     * Remove Index from list of waitlisted Indexes that the Student is registered
     * for
     *
     * @param index The waitlisted Index that is to be removed from teh list of
     *              WaitListed Indexes
     */
    public void removeFromWaitlistedIndexArray(Index index) {
        waitlistedIndexArray.remove(index);
    }

    /**
     * Moves the Index from the confirmed List of Indexes to the waitlist List of
     * Indexes
     * 
     * @param index The Index to be moved from the waitlist List of Indexes to the
     *              confirmed List of Indexes
     */
    public void moveToConfirmed(Index index) {
        confirmedIndexArray.add(index);
        waitlistedIndexArray.remove(index);
    }

    /**
     * Gets the Student that the StudentRegisteredCourses belongs to
     * 
     * @return The owner of the StudentRegisteredCourses
     */
    public Student getOwner() {
        return owner;
    }

    /**
     * Adds Index to StudentRegisteredCourses and adds Student to the Index
     * 
     * @param index The Index to be added
     * @return boolean that indicates false if the Student has been added to the
     *         waitlist of the Index or true if the confirmed list of the Index
     * @throws ExceedAUException          If the user is unable to add the Index to
     *                                    the list of Registered Courses because it
     *                                    would exceed the AU limit
     * @throws TimetableClashException    If the user is unable to add the Index to
     *                                    the list of Registered Courses because of
     *                                    a Timetable Clash between te Index and the
     *                                    Student's exisiting Indexes
     * @throws AlreadyRegisteredException If the user is unable to add the Index
     *                                    because he has already Registered for the
     *                                    Course
     */
    public boolean addIndex(Index index) throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        IndexStudentAdder indexStudentAdder = new IndexStudentAdder();
        boolean success = indexStudentAdder.addStudent(index, this);
        return success;
    }

    /**
     * Drops the Index from StudentRegisteredCourses and drops Student from the
     * Index
     * 
     * @param index The Index to be dropped
     * @return boolean that indicates whether Index has been dropped from
     *         StudentRegisteredCourses and from the Index
     */
    public boolean dropIndex(Index index) {
        IndexStudentDropper indexStudentDropper = new IndexStudentDropper();
        boolean success = indexStudentDropper.dropStudent(index, this);
        return success;
    }

    /**
     * Changes the Index for a Student to another Index of the same Course. This
     * change is also reflected in both Indexes
     * 
     * @param oldIndex The current Index of the Student
     * @param newIndex The new Index of the Student
     * @return boolean that indicates whether the Student has been added to the
     *         Waitlist of the Index or the Confirmed list of the Index
     * @throws ExceedAUException          If the user is unable to add the Index to
     *                                    the list of Registered Courses because it
     *                                    would exceed the AU limit
     * @throws TimetableClashException    If the user is unable to add the Index to
     *                                    the list of Registered Courses because of
     *                                    a Timetable Clash between te Index and the
     *                                    Student's existing Indexes
     * @throws AlreadyRegisteredException If the user is unable to add the Index
     *                                    because he has already Registered for the
     *                                    Course
     */
    public boolean changeIndex(Index oldIndex, Index newIndex)
            throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        TimetableClashChecker clashChecker = new TimetableClashChecker();
        if (oldIndex.getIndexNumber() == newIndex.getIndexNumber()) {
            throw new AlreadyRegisteredException();
        }
        boolean allowed = clashChecker.checkClash(this, newIndex, oldIndex);
        if (allowed) {
            IndexStudentAdder adder = new IndexStudentAdder();
            IndexStudentDropper dropper = new IndexStudentDropper();
            dropper.dropStudent(oldIndex, this);
            allowed = adder.addStudent(newIndex, this);
            return allowed;
        }
        throw new TimetableClashException();
    }

    /**
     * Swaps the Index of a Student with the Index of another Student in the same
     * Course. This wil be reflected in both Students and both Indexes
     * 
     * @param friendIndex The Index of the other Student in the same Course
     * @param friend      The other Student
     * @return boolean that indicates true if the current Student has been swopped
     *         to the Confirmed list of the other person's Index or false if the
     *         Waitlist of the other person's Index
     * @throws TimetableClashException    If the user is unable to add the Index to
     *                                    the list of Registered Courses because of
     *                                    a Timetable Clash between te Index and the
     *                                    Student's existing Indexes
     * @throws AlreadyRegisteredException If the user is unable to add the Index
     *                                    because he has already Registered for the
     *                                    Course
     */
    public boolean swopPlaces(Index friendIndex, Student friend)
            throws TimetableClashException, AlreadyRegisteredException {
        IndexStudentSwopper swopper = new IndexStudentSwopper();
        return swopper.swopStudent(friendIndex, friend, this.owner);
    }
}
