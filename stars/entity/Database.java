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
	
	public boolean addStudent(Student student) {
		for (Student curStudent: studentArray) {
			if (curStudent.getStudentID().equals(student.getStudentID())) {
				return false;
			}
		}
		studentArray.add(student);
		return true;
	}
	
	public boolean addCourse(Course course) {
		for (Course curCourse: coursesArray) {
			if (curCourse.getCourseID().equals(course.getCourseID())) {
				return false;
			}
		}
		coursesArray.add(course);
		return true;
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

