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
		for (Timing timing : timings) {
			if (!timing.checkOverlap(newTiming)) {
				return false;
			}
		}
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

	public Student dequeueStudent() {
		return waitList.remove(0);
	}

	public void insertStudentToWaitList(Student student, int location) {
		waitList.add(location, student);
	}

	public int getAU() {
		return this.course.getAu();
	}


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
		String stringToReturn = "Index: "+ String.valueOf(this.indexNumber) + " vacancy: " + String.valueOf(this.vacancy) + " confirmed size: " + this.confirmedList.size() + " waitlist size: " + this.waitList.size() ;
		return stringToReturn;
	}

	public int getVacancyLimit() {
		return vacancyLimit;
	}

	public void setVacancyLimit(int newvacancyLimit) {
		this.vacancyLimit = newvacancyLimit;
	}
}