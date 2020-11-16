package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains the Information regarding the Students and Courses
 */
public class Database implements Serializable {
	private ArrayList<Student> studentArray;
	private ArrayList<Course> coursesArray;

	/**
	 * Constructor of the Database
	 */
	public Database() {
		studentArray = new ArrayList<Student>();
		coursesArray = new ArrayList<Course>();
	}

	/**
	 * Gets the List of Students
	 * 
	 * @return ArrayList of Students
	 */
	public ArrayList<Student> getStudentArray() {
		return studentArray;
	}

	/**
	 * Gets the List of Courses
	 * 
	 * @return ArrayList of Courses
	 */
	public ArrayList<Course> getCoursesArray() {
		return coursesArray;
	}

	/**
	 * Adds the Student to the ArrayList of Students
	 * 
	 * @param student Student that is to be added to ArrayList of Students
	 * @return boolean that indicates whether the student has been added to the
	 *         ArrayList
	 */
	public boolean addStudent(Student student) {
		for (Student curStudent : studentArray) {
			if (curStudent.getStudentID().equals(student.getStudentID())) {
				return false;
			}
		}
		studentArray.add(student);
		return true;
	}

	/**
	 * Adds Course to the ArrayList of Courses
	 * 
	 * @param course Course to be added to the ArrayList of Courses
	 * @return boolean that indicates whether a Course has been added to the
	 *         ArrayList of Courses
	 */
	public boolean addCourse(Course course) {
		for (Course curCourse : coursesArray) {
			if (curCourse.getCourseID().equals(course.getCourseID())) {
				return false;
			}
		}
		coursesArray.add(course);
		return true;
	}

	/**
	 * Finds and returns a Student from the ArrayList of Students
	 * 
	 * @param studentID Student ID to use for finding the Student in the ArrayList
	 *                  of Students
	 * @return Student that is to be found
	 */
	public Student findStudent(String studentID) {
		for (Student student : this.studentArray) {
			if ((student.getStudentID()).equals(studentID)) {
				return student;
			}
		}
		return null;
	}
}
