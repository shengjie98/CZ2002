package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.exceptions.*;
import stars.boundary.*;

/**
 * Controller to manage Student User
 */
public class StudentController {

    private Student myStudent;
    private DatabaseManager dbManager;
    private TimetableDisplayer displayer;

    /**
     * Constructor for StudentController
     * 
     * @param studentID The ID of the Student
     */
    public StudentController(String studentID) {
        dbManager = new DatabaseManager();
        myStudent = dbManager.findStudent(studentID);
        displayer = new TimetableDisplayer(myStudent);
    }

    /**
     * Gets List of Courses that is in the Database
     * 
     * @return The ArrayList of Courses in the database
     */
    public ArrayList<Course> getCourseList() {
        return this.dbManager.getCoursesArray();
    }

    /**
     * Adds the Index to the Student's StudentRegisteredCourses and adds the Student
     * to the Index's List of Students
     * 
     * @param myIndex The Index to be added to the Student's
     *                StudentRegisteredCourses
     * @return Boolean that indicates true if the Index has been added to the
     *         confirmed list, false if it has been added to the waitlist
     * @throws ExceedAUException            If the user is unable to add the Index
     *                                      to the list of Registered Courses
     *                                      because it would exceed the AU limit
     * @throws TimetableClashException      If the user is unable to add the Index
     *                                      to the list of Registered Courses
     *                                      because of a Timetable Clash between te
     *                                      Index and the Student's exisiting
     *                                      Indexes
     * @throws AlreadyRegisteredExceptionIf the user is unable to add the Index
     *                                      because he has already Registered for
     *                                      the Course
     */
    public boolean addIndex(Index myIndex)
            throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        return myStudent.addIndex(myIndex);
    }

    public boolean dropIndex(Index index) {
        return myStudent.dropIndex(index);
    }

    public ArrayList<Index> getRegisteredIndex() {
        return myStudent.getRegisteredIndex();
    }

    public ArrayList<Index> getConfirmedIndex() {
        return myStudent.getConfirmedIndex();
    }

    public ArrayList<Index> getWaitlistedIndex() {
        return myStudent.getWaitlistedIndex();
    }

    public boolean changeIndex(Index oldIndex, Index newIndex)
            throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        return myStudent.changeIndex(oldIndex, newIndex);
    }

    public boolean swopIndex(String friendID, Index myIndex)
            throws TimetableClashException, AlreadyRegisteredException {
        // using myIndex find myCourse, then find my friend's Index using myCourse
        Student friend = dbManager.findStudent(friendID); // find the student object for your friend
        Course myCourse = myIndex.getCourse(); // from the student object, find
        for (Index friendIndex : friend.getRegisteredIndex()) {
            Course friendCourse = friendIndex.getCourse();
            if (friendCourse.getCourseID().equals(myCourse.getCourseID())) {// id
                // check that index is not the same
                if (friendIndex.getIndexNumber() == myIndex.getIndexNumber()) {
                    throw new AlreadyRegisteredException();
                }

                if (friend.checkChangeIndex(myIndex, friendIndex) && myStudent.checkChangeIndex(friendIndex, myIndex)) {
                    friend.swopPlaces(myIndex, this.myStudent);
                    return myStudent.swopPlaces(friendIndex, friend);
                }
            }
        }
        throw new TimetableClashException();
    }

    public void save() {
        this.dbManager.saveInformation();
    }

    public void displayTimetable() {
        this.displayer.displayTimetable();
    }

}
