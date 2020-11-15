package stars.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import stars.entity.*;

public class DatabaseManager {
    private Database db;
    private final String DATABASE_FILE = "stars/database.ser";

    public ArrayList<Student> getStudentArray() {
        return db.getStudentArray();
    }

    public ArrayList<Course> getCoursesArray() {
        return db.getCoursesArray();
    }

    public boolean addStudent(Student student) {
        return db.addStudent(student);
    }

    public boolean addCourse(Course course) {
        return db.addCourse(course);
    } 

    public Student findStudent(String studentID) {
        return db.findStudent(studentID);
    }

    public void loadInformation() {
        try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(DATABASE_FILE));
			this.db = (Database) inputStream.readObject();
            inputStream.close();
            if (this.db == null) {
                System.out.println("Empty database");
                this.db = new Database();
            }
		} catch (IOException e) {
			System.out.println("IOException");
			this.db = new Database();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
			this.db = new Database();
		}
    }

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
