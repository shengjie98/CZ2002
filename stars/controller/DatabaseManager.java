package stars.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import stars.entity.*;

/**
 * Database manager class to access database, handles database and hides
 * implementation of database from client
 */
public class DatabaseManager {
    ;
    private final String DATABASE_FILE = "stars/database.ser";

    private Database db   /**
     * Constructor for DatabaseManager
     */
    public DatabaseManager() {
        loadInformation();
    }

    /**
     * Gets all students from the database
     * 
     * @return ArrayList of students from database
     */
    public ArrayList<Student> getStudentArray() {
        return db.getStudentArray();
    }

    /**
     * Gets all courses from the database
     * 
     * @return ArrayList of courses from database
     */
    public ArrayList<Course> getCoursesArray() {
        return db.getCoursesArray();
    }

    /**
     * Adds a student to the database
     * 
     * @param student Student object to be added to the database
     * @return true if added successfully, false if studentID already exists in the
     *         database
     */
    public boolean addStudent(Student student) {
        return db.addStudent(student);
    }

    /**
     * Adds a course to the database
     * 
     * @param course Course object to be added to the database
     * @return true if added successfullym false if courseID already exists in the
     *         database
     */
    public boolean addCourse(Course course) {
        return db.addCourse(course);
    }

    /**
     * Get student object from database using username
     * 
     * @param studentID username of student to be found
     * @return Student object with matching studentID
     */
    public Student findStudent(String studentID) {
        return db.findStudent(studentID);
    }

    /**
     * Method to read serialised files from disk and load the database into memory
     */
    private void loadInformation() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATABASE_FILE));
            this.db = (Database) inputStream.readObject();
            inputStream.close();
            if (this.db == null) {
                // System.out.println("Empty database");
                this.db = new Database();
            }
        } catch (IOException e) {
            // System.out.println("IOException");
            this.db = new Database();
        } catch (ClassNotFoundException e) {
            // System.out.println("Class not found");
            this.db = new Database();
        }
    }

    /**
     * Method to write databse from memory to disk as serialised file
     */
    public void saveInformation() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(DATABASE_FILE));
            outputStream.writeObject(this.db);
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Class not found");
        }
    }
}
