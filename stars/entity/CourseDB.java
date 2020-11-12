package stars.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

/**
 * Handles the logic dealing with the course data
 */
public class CourseDB implements Database {

    /**
     * Initialises a list
     */
    private ArrayList<Course> courseList = new ArrayList<Course>();
    private final String COURSE_DATABASE_FILE = "stars/courses.ser";

    public CourseDB() {
        this.loadInformation();
    }



    /**
     * Retrieves the list of all the courses in the database
     * 
     * @return list of all courses in database
     */
    public ArrayList getList() {
        return this.courseList;
    }

    /**
     * Add a new course into the database
     * 
     * @param course to be added into database
     */
    public void addItem(Object newCourse) {
        courseList.add((Course)newCourse);
    }

    public void loadInformation() {
        try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(COURSE_DATABASE_FILE));
            this.courseList = (ArrayList<Course>) inputStream.readObject();
            // inputStream.close();
		} catch (IOException e) {
            // System.out.println("IOException");
            this.courseList = new ArrayList<Course>();
		} catch (ClassNotFoundException e) {
            // System.out.println("Class not found");
            this.courseList = new ArrayList<Course>();
		}
    }
    
    public void saveInformation() {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(COURSE_DATABASE_FILE));
			outputStream.writeObject(this.courseList);
			outputStream.close();
		} catch (IOException e) {
            System.out.println("Class not found");
		}
    }

}
