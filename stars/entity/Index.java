package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

/**
 * In charge of handling the data that belongs to an Index
 */
public class Index implements Selectable, Serializable {
	private Course course;
	private ArrayList<Timing> timings = new ArrayList<Timing>();;
	private ArrayList<Student> waitList = new ArrayList<Student>();
	private ArrayList<Student> confirmedList = new ArrayList<Student>();
	private int vacancy;
	private int vacancyLimit;
	private int indexNumber;

	/**
	 * Constructor for Index
	 * 
	 * @param course
	 * @param vacancy
	 * @param indexNumber
	 */
	public Index(Course course, int vacancy, int indexNumber) {
		this.course = course;
		this.vacancy = vacancy;
		this.vacancyLimit = vacancy;
		this.indexNumber = indexNumber;
	}

	/**
	 * Adds Timing to ArrayList of Timings
	 * 
	 * @param newTiming Timing to be added
	 * @return boolean that indicates whether a Timing was added to the ArrayList of
	 *         Timings
	 */
	public boolean addTiming(Timing newTiming) {
		for (Timing timing : timings) {
			if (!timing.checkOverlap(newTiming)) {
				return false;
			}
		}
		timings.add(newTiming);
		return true;
	}

	/**
	 * Adds Student to the waitlist of the Index
	 * 
	 * @param student Student to be added to the waitlist of the Index
	 */
	public void addStudentToWaitList(Student student) {
		waitList.add(student);
	}

	/**
	 * Adds Student to the confirmed list of the Index
	 * 
	 * @param student Student to be added ot the confirmed list of the Index
	 */
	public void addStudentToConfirmedList(Student student) {
		confirmedList.add(student);
		vacancy--;
	}

	/**
	 * Drops Student from waitlist of the Index
	 * 
	 * @param student Student to be dropped from the waitlist of the Index
	 */
	public void dropStudentFromWaitList(Student student) {
		waitList.remove(student);
	}

	/**
	 * Drops Student from the confirmed list of the Index
	 * 
	 * @param student Student to be droppped from the confirmed list of the Index
	 */
	public void dropStudentFromConfirmedList(Student student) {
		confirmedList.remove(student);
		vacancy++;
	}

	/**
	 * Removes the Student from the front of the waitlist
	 * 
	 * @return Student that is to be removed from te front of the waitlist
	 */
	public Student dequeueStudent() {
		return waitList.remove(0);
	}

	/**
	 * Inserts the Student into the Index in the waitlist and pushes back all the
	 * other Students
	 * 
	 * @param student  Student to be added to waitlist
	 * @param location The Index where the Student is to be added to the waitlist
	 */
	public void insertStudentToWaitList(Student student, int location) {
		waitList.add(location, student);
	}

	/**
	 * Gets the AUs of the Course
	 * 
	 * @return The AU of the Course
	 */
	public int getAU() {
		return this.course.getAu();
	}

	/**
	 * Gets the Course that the Index belongs to
	 * 
	 * @return The Course that the Index belongs to
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * Sets the Course that the Index belongs to
	 * 
	 * @param course The Course that the Index belongs to
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * Gets the ArrayList of Timings that the Index has
	 * 
	 * @return The ArrayList of Timings of the Index
	 */
	public ArrayList<Timing> getTimings() {
		return timings;
	}

	/**
	 * Sets the ArrayList of Timings
	 * 
	 * @param timings ArrayList of Timings of the Index
	 */
	public void setTimings(ArrayList<Timing> timings) {
		this.timings = timings;
	}

	/**
	 * Gets the vacancy of the Index
	 * 
	 * @return The vacancy of the Index
	 */
	public int getVacancy() {
		return vacancy;
	}

	/**
	 * Sets the vacancy of the Index
	 * 
	 * @param vacancy The vacancy of the Index
	 */
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}

	/**
	 * Gets the waitlist of Students of the Index
	 * 
	 * @return The waitlist of Students of the Index
	 */
	public ArrayList<Student> getWaitList() {
		return waitList;
	}

	/**
	 * Sets the waitlist of the Index
	 */
	public void setWaitList(ArrayList<Student> waitList) {
		this.waitList = waitList;
	}

	/**
	 * Gets the confirmed list of the Index
	 * 
	 * @return The confirmed list of the Index
	 */
	public ArrayList<Student> getConfirmedList() {
		return confirmedList;
	}

	/**
	 * Sets the confirmed list of Students of the Index
	 * 
	 * @param confirmedList The confirmed list of Students of the Index
	 */
	public void setConfirmedList(ArrayList<Student> confirmedList) {
		this.confirmedList = confirmedList;
	}

	/**
	 * Gets the Index number of the Index
	 * 
	 * @return
	 */
	public int getIndexNumber() {
		return indexNumber;
	}

	/**
	 * Sets the Index Number of the Index
	 * 
	 * @param indexNumber The Index Number of the Index
	 */
	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}

	/**
	 * Prints out the information of the Index
	 * 
	 * @return the String that contains the information of the Index
	 */
	public String print() {
		// print out all the information of the Course
		String stringToReturn = "Index: " + String.valueOf(this.indexNumber) + " vacancy: "
				+ String.valueOf(this.vacancy) + " confirmed size: " + this.confirmedList.size() + " waitlist size: "
				+ this.waitList.size();
		return stringToReturn;
	}

	/**
	 * Gets the Vacancy Limit of the Index
	 * 
	 * @return Vacancy Limit of the Index
	 */
	public int getVacancyLimit() {
		return vacancyLimit;
	}

	/**
	 * Sets the Vacancy Limit of the Index
	 * 
	 * @param newvacancyLimit Vacancy Limit of the Index
	 */
	public void setVacancyLimit(int newvacancyLimit) {
		this.vacancyLimit = newvacancyLimit;
	}
}