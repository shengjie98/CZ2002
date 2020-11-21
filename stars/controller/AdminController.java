package stars.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import stars.entity.*;
import stars.entity.Timing.Day;
import stars.entity.Timing.Type;

/**
 * Controller to manage the admin user
 */
public class AdminController implements UserControllerInterface {
    private DatabaseManagerInterface dbManager;

    /**
     * Constructor for AdminConstructor, to initilaise database
     */
    public AdminController() {
        dbManager = new DatabaseManager();
    }

    /**
     * Gets the ArrayList of all courses in the database
     * 
     * @return ArrayList of all courses in the database
     */
    public ArrayList<Course> getCourseList() {
        return this.dbManager.getCoursesArray();
    }

    /**
     * Gets the ArrayList of all students in the database
     * 
     * @return ArrayList of all students in the database
     */
    public ArrayList<Student> getStudentList() {
        return this.dbManager.getStudentArray();
    }

    /**
     * method to serilaise the database back to disk
     */
    public void save() {
        this.dbManager.saveInformation();
    }

    /**
     * Creates new Index object
     * 
     * @param selectedCourse Course that index belongs to
     * @param newVacancy     Vacancy of the new index to be created
     * @param newIndexNumber Index number of the new index to be created
     * @return Index object that was created
     */
    public Index createIndex(Course selectedCourse, int newVacancy, int newIndexNumber) {
        Index newIndex = new Index(selectedCourse, newVacancy, newIndexNumber);
        return newIndex;
    }

    /**
     * Creates a new Timing Object
     * 
     * @param day   day that this class is held
     * @param type  the dype of class that is held
     * @param start the start time of the class
     * @param end   the end time of the class
     * @return Timing object that was created
     */
    public Timing createTiming(Day day, Type type, LocalTime start, LocalTime end) {
        Timing newTiming = new Timing(day, type, start, end);
        return newTiming;
    }

    /**
     * Creates a new Course Object
     * 
     * @param newCourseID   the course ID of the course to be created
     * @param newAU         Number of AU of the new course
     * @param newSchool     School that is teaching the course
     * @param newCourseName course name
     * @return Course object that was created
     */
    public Course createCourse(String newCourseID, int newAU, String newSchool, String newCourseName) {
        Course newCourse = new Course(newCourseID, newAU, newSchool, newCourseName);
        return newCourse;
    }

    /**
     * add a Timing object to an Index object
     * 
     * @param newIndex  index which Timing object will be added to
     * @param newTiming Timing which will be added to Index Object
     * @return true if there is no overlap with exisiting timing object in the
     *         Index, the timing would be successfully added. false if there is an
     *         overlap with exisiting timing object in Index. Timing will not be
     *         added
     */
    public boolean addTiming(Index newIndex, Timing newTiming) {
        if (newIndex.addTiming(newTiming)) {
            return true;
        }
        return false;
    }

    /**
     * Creates and adds a new Student object to the database
     * 
     * @param studentName Name of student to be added
     * @param nationality nationality of student to be added
     * @param gender      gender of student to be added
     * @param studentID   ID of student to be added, will be the username for login
     * @param degree      degree of student to be added
     * @param email       email of student to be added
     * @param password    password of student to be added
     * @param start       access period start of student to be added
     * @param end         access period end of student to be added
     * @return true if new Student is added successfully, false if student already
     *         exists in the database
     */
    public boolean addStudent(String studentName, String nationality, String gender, String studentID, String degree,
            String email, String password, LocalDateTime start, LocalDateTime end) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree, email);
        boolean success;
        // add this student to the StudentDB
        success = dbManager.addStudent(newStudent);
        if (success) {
            StudentAuthenticator studentAuthenticator = new FlatFileStudentAuthenticator();
            studentAuthenticator.addStudent(studentID, password, start, end);
        }
        return success;
    }

    /**
     * Add a Course Object to the database
     * 
     * @param newCourse Course to be added to the database
     * @return true if new Course is added successfully, false if course already
     *         exist in the database
     */
    public boolean addCourse(Course newCourse) {
        return dbManager.addCourse(newCourse);
    }

    /**
     * Set the courseid of the selected course with the new id
     * 
     * @param selectedCourse Course whose courseid will be changed
     * @param newCourseID    New courseid
     * @return true of changed successfully, false if new courseid already exists in
     *         the database
     */
    public boolean setCourseID(Course selectedCourse, String newCourseID) {
        ArrayList<Course> courseList = dbManager.getCoursesArray();
        for (Course course : courseList) {
            if (newCourseID.equals(course.getCourseID())) {
                return false;
            }
        }
        selectedCourse.setCourseID(newCourseID);
        return true;
    }

    /**
     * Set the vacancy limit to an index Can increase the vacancy limit of the
     * index, which will dequeue waitlisted students and confirm their positions and
     * send notifications Can decrease the vacancy limit as long the number of
     * confirmed students is still within the limit Cannot decrease the vacancy
     * limit if the new vacancy limit is less than the number of confirmed students
     * in the index
     * 
     * @param selectedIndex   Index whose vacancy will be changed
     * @param newVacancyLimit new vacancy limit
     * @return true if successfully changed, false if new vacancy limit is less the
     *         the number of confirmed students in the index
     */
    public boolean setVacancyLimit(Index selectedIndex, int newVacancyLimit) {
        SetVacancyLimitController setVacancyLimitController = new SetVacancyLimitController();
        return setVacancyLimitController.setVacancyLimit(selectedIndex, newVacancyLimit);
    }

    /**
     * Add an Index object to a Course object
     * 
     * @param selectedCourse Course which will add a new Index
     * @param newIndex       Index object to be added to the course
     * @return true if added successfully, false if index number already exists in
     *         the course
     */
    public boolean addIndex(Course selectedCourse, Index newIndex) {
        if (selectedCourse.addIndex(newIndex)) {
            return true;
        }
        return false;
    }

    /**
     * Set the IndexID of an Index to a new value
     * 
     * @param selectedIndex Index object whose IndexID will be changed
     * @param newIndexID    new Index ID
     * @return true if added successfully, false if index new index number already
     *         exists in the course
     */
    public boolean changeIndexID(Index selectedIndex, int newIndexID) {
        Course course = selectedIndex.getCourse();
        ArrayList<Index> indexList = course.getIndexList();
        for (Index index : indexList) {
            if (index.getIndexNumber() == newIndexID) {
                return false;
            }
        }
        selectedIndex.setIndexNumber(newIndexID);
        return true;
    }

    /**
     * Drop index from course
     * 
     * @param selectedCourse Course whose index will be dropped
     * @param selectedIndex  Index to be dropped from the course
     * @return true if index is empty and can be dropped, false if index has
     *         confirmed students, index will not be dropped
     */
    public boolean dropIndex(Course selectedCourse, Index selectedIndex) {
        ArrayList<Course> listOfCourses = dbManager.getCoursesArray();
        // find the ourse in the list of courses and remove it
        for (Course eachCourse : listOfCourses) {
            if (eachCourse.equals(selectedCourse)) {
                ArrayList<Student> confirmedList = selectedIndex.getConfirmedList();
                if (confirmedList.size() == 0) {
                    (eachCourse.getIndexList()).remove(selectedIndex);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * edit access of a student
     * 
     * @param studentID studentid and username of the user
     * @param start     new start of access period of student
     * @param end       new end of access period of student
     */
    public void editAccess(String studentID, LocalDateTime start, LocalDateTime end) {
        StudentAuthenticator studentAuthenticator = new FlatFileStudentAuthenticator();
        studentAuthenticator.editAccess(studentID, start, end);
    }
}
