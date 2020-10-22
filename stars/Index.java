package stars;

import java.util.ArrayList;

public class Index implements Selectable {
	private Course course;
	private ArrayList<Timing> timings;
	private ArrayList<Student> waitList;
	private ArrayList<Student> confirmedList;
	private int vacancy;
	private int indexNumber;

	public Index(Course course, ArrayList<Timing> timings, int vacancy, ArrayList<Student> waitList,
			ArrayList<Student> confirmedList, int indexNumber) {
		this.course = course;
		this.timings = timings;
		this.vacancy = vacancy;
		this.waitList = waitList;
		this.confirmedList = confirmedList;
		this.indexNumber = indexNumber;
	}

	/**
	 * 
	 * @param newStudent
	 * @return returns true if student is added to confirmed list and false if added
	 *         to waitlist
	 */
	public boolean addStudent(Student newStudent) {
		// if the confirmedList is full, add the student to the waitlist
		if (confirmedList.size() == vacancy) {
			waitList.add(newStudent);
			return false;
		} else {
			// else add the student to the confirmed list
			confirmedList.add(newStudent);
			return true;
		}
	}

	/**
	 * 
	 * @param newStudent
	 * @return
	 */
	public boolean dropStudent(Student newStudent) {
		if (confirmedList.contains(newStudent)) {
			confirmedList.remove(newStudent);
			return true;
		} else {
			waitList.remove(newStudent);
			return false;
		}
	}

	public boolean swapStudent(Student newStudent) {
		if (confirmedList.contains(newStudent)) {
			confirmedList.set(confirmedList.indexOf(newStudent), newStudent);
			return true;
		} else {
			waitList.set(waitList.indexOf(newStudent), newStudent);
			return false;
		}
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
		String stringToReturn = String.valueOf(this.getIndexNumber()) + ' ' + String.valueOf(this.getVacancy());
		return stringToReturn;
	}
}