package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

public class Index implements Selectable, Serializable {
	private Course course;
	private ArrayList<Timing> timings = new ArrayList<Timing>();;
	private ArrayList<Student> waitList = new ArrayList<Student>();
	private ArrayList<Student> confirmedList = new ArrayList<Student>();
	private int vacancy;
	private int vacancyLimit;
	private int indexNumber;

	public Index(Course course, int vacancy, int indexNumber) {
		this.course = course;
		this.vacancy = vacancy;
		this.vacancyLimit = vacancy;
		this.indexNumber = indexNumber;
	}

	public boolean addTiming(Timing newTiming) {
		timings.add(newTiming);
		return true;
	}

	public void addStudentToWaitList(Student student) {
		waitList.add(student);
	}

	public void addStudentToConfirmedList(Student student) {
		confirmedList.add(student);
		vacancy--;
	}

	public void dropStudentFromWaitList(Student student) {
		waitList.remove(student);
	}

	public void dropStudentFromConfirmedList(Student student) {
		confirmedList.remove(student);
		vacancy++;
	}

	public Student dequeueStudent(){
		return waitList.remove(0);
	}

	public void insertStudentToWaitList(Student student, int location) {
		waitList.set(location, student);
	}

	public int getAU() {
		return this.course.getAu();
	}


	// public boolean addStudent(Student newStudent) {
	// 	// if the confirmedList is full, add the student to the waitlist
	// 	if (confirmedList.size() == vacancyLimit) {
	// 		waitList.add(newStudent);
	// 		return false;
	// 	} else {
	// 		// else add the student to the confirmed list
	// 		confirmedList.add(newStudent);
	// 		vacancy--;
	// 		return true;
	// 	}
	// }

	// public boolean dropStudent(Student newStudent) {
	// 	if (confirmedList.contains(newStudent)) {
	// 		confirmedList.remove(newStudent);
	// 		vacancy++;
	// 		// TODO missing adding the waitlist student
	// 		return true;


	// 	} else {
	// 		waitList.remove(newStudent);
	// 		return false;
	// 	}
	// }

	// public boolean swopStudent(Student curStudent, Student newStudent) {
	// 	if (confirmedList.contains(curStudent)) {
	// 		confirmedList.set(confirmedList.indexOf(curStudent), newStudent);
	// 		return true;
	// 	} else {
	// 		waitList.set(waitList.indexOf(curStudent), newStudent);
	// 		return false;
	// 	}
	// }

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ArrayList<Timing> getTimings() {
		return timings;
	}

	public void setTimings(ArrayList<Timing> timings) {
		this.timings = timings;
	}

	public int getVacancy() {
		return vacancy;
	}

	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	public ArrayList<Student> getWaitList() {
		return waitList;
	}

	public void setWaitList(ArrayList<Student> waitList) {
		this.waitList = waitList;
	}

	public ArrayList<Student> getConfirmedList() {
		return confirmedList;
	}

	public void setConfirmedList(ArrayList<Student> confirmedList) {
		this.confirmedList = confirmedList;
	}

	public int getIndexNumber() {
		return indexNumber;
	}

	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

	public String print() {
		// print out all the information of the Course
		String stringToReturn = String.valueOf(this.getIndexNumber()) + ' ' + String.valueOf(this.getVacancy());
		return stringToReturn;
	}
}