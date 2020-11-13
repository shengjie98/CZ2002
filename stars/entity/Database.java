package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

public class Database implements Serializable{ 
	private ArrayList<Student> studentArray;
	private ArrayList<Course> coursesArray;
	
	public Database() {
		studentArray = new ArrayList<Student>();
		coursesArray = new ArrayList<Course>();
	}
	
	public ArrayList<Student> getStudentArray() {
		return studentArray;
	}
	
	public ArrayList<Course> getCoursesArray() {
		return coursesArray;
	}
	
	public void addStudent(Student student) {
		studentArray.add(student);
	}
	
	public void addCourse(Course course) {
		coursesArray.add(course);
	}

	public Student findStudent(String studentID) {
		for (Student student : this.studentArray) {
			if ((student.getStudentID()).equals(studentID)) {
				return student;
			}
		}
		return null;
	}
}

