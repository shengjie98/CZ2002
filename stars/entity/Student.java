package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.controller.*;
import stars.exceptions.*;

/**
 * In charge of handling student data
 */
public class Student implements Selectable, Serializable {

    private String studentID;
    private String studentName;
    private String nationality;
    private String gender;
    private String degree;
    private StudentRegisteredCourses registeredCourses;
    private String email;
    private NotificationInterface notificationService;

    /**
     * Constructor for Student
     * 
     * @param studentName
     * @param nationality
     * @param gender
     * @param studentID
     * @param degree
     * @param email
     */
    public Student(String studentName, String nationality, String gender, String studentID, String degree,
            String email) {
        this.studentName = studentName;
        this.nationality = nationality;
        this.gender = gender;
        this.studentID = studentID;
        this.degree = degree;
        this.email = email;
        notificationService = new EmailService(this);
        registeredCourses = new StudentRegisteredCourses(this);

    }

    /**
     * Adds Index to the StudentRegisteredCourses
     * 
     * @param index The Index to be added to the StudentRegisteredCourse
     * @return Boolean that indicates false if the Student has been added to the
     *         waitlist of the Index
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
        return registeredCourses.addIndex(index);
    }

    /**
     * Drops Index from StudentRegisteredCourses
     * 
     * @param index Index to be dropped from StudentRegisteredCourses
     * @return boolean that indicates whether Index has been dropped from
     *         StudentRegisteredCourses and from the Index
     */
    public boolean dropIndex(Index index) {
        return registeredCourses.dropIndex(index);
    }

    /**
     * Moves Index from waitlist to confirmed list in StudentRegisteredCourses
     * 
     * @param index Index to be moved from waitlist to confirmed list
     */
    public void moveToConfirmed(Index index) {
        registeredCourses.moveToConfirmed(index);
    }

    /**
     * Gets the confirmed list of Indexes that Student has registered for
     * 
     * @return ArrayList of confirmed Indexes
     */
    public ArrayList<Index> getConfirmedIndex() {
        return registeredCourses.getConfirmedIndexArray();
    }

    /**
     * Gets the Indexes that the Student is waitlisted for
     * 
     * @return ArrayList of confirmed Indexes
     */
    public ArrayList<Index> getWaitlistedIndex() {
        return registeredCourses.getWaitlistedIndexArray();
    }

    /**
     * Gets the merged list of Indexes from both the confirmed list and the waitlist
     * 
     * @return ArrayList of all Indexes, both confirmed and on waitlist that the
     *         Student has registered for
     */
    public ArrayList<Index> getRegisteredIndex() {
        return registeredCourses.getIndexList();
    }

    /**
     * Changes the Index of the Student from the current Index to a new Index of the
     * same Course. This change is also reflected in the Indexes.
     * 
     * @param oldIndex The current Index that Student has been registered for
     * @param newIndex Teh new Index that the Student will be changed to
     * @return boolean that indicates true if the Student has been added to the
     *         confirmed list or false if the Student has been added to the waitlist
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
    public boolean changeIndex(Index oldIndex, Index newIndex)
            throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        return registeredCourses.changeIndex(oldIndex, newIndex);
    }

    /**
     * Checks if there will be a clash in the timetable of the student if the
     * Current Index of a Course is swopped with Index of another Student taking the
     * same Course
     * 
     * @param friendIndex The Index of the other Student
     * @param myIndex     The Index of the current Student
     * @return boolean that indicates whether there is a Timetable clash
     */
    public boolean checkChangeIndex(Index friendIndex, Index myIndex) {
        TimetableClashChecker clashChecker = new TimetableClashChecker();
        return clashChecker.checkClash(this.registeredCourses, friendIndex, myIndex);
    }

    /**
     * Swops the Index of the Student with the Index of another Student taking the
     * same Course. This will be reflected in the other Student as well.
     * 
     * @param friendIndex The Index of the other Student
     * @param friend      The other Student
     * @return boolean that indicates true if the current Student has been swopped
     *         to the Confirmed list of the other person's Index or false if the
     *         Waitlist of the other person's Index
     * @throws TimetableClashException
     * @throws AlreadyRegisteredException
     */
    public boolean swopPlaces(Index friendIndex, Student friend)
            throws TimetableClashException, AlreadyRegisteredException {
        return registeredCourses.swopPlaces(friendIndex, friend);
    }

    /**
     * Sends Notification to Student's Email to inform them of the fact that they
     * have been moved from the waitlist of an Index to the confrimed list of the
     * Index. This occurs when another Student is removed from the confirmed list of
     * the same Index that the current Student is being waitlisted for
     * 
     * @param index The index that in which the Student has been moved from the
     *              waitllist to the confirmed list
     */
    public void sendNotification(Index index) {
        this.notificationService.sendNotification(index);
    }

    /**
     * Prints out the information of the Student
     * 
     * @return The Information of the Student
     */
    public String print() {

        String stringToReturn = "Name: " + this.studentName + " Gender: " + this.gender + " Nationality: "
                + this.nationality;
        return stringToReturn;
    }

    /**
     * Gets the StudentRegisteredCourses of the Student
     * 
     * @return The StudentRegisteredCourses of the Student
     */
    public StudentRegisteredCourses getregisteredCourses() {
        return registeredCourses;
    }

    /**
     * Gets the Student ID of the Student
     * 
     * @return Student ID of the Student
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Gets the name of the Student
     * 
     * @return Name of the Student
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the Nationality of the Student
     * 
     * @return The nationality of the Student
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Gets the gender of the Student
     * 
     * @return The gender of the Student
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the degree of the Student
     * 
     * @return Degree of the Student
     */
    public String getDegree() {
        return degree;
    }

    /**
     * Gets the email of the Student
     * 
     * @return Email of the Student
     */
    public String getEmail() {
        return email;
    }

}
