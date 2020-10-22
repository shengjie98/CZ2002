package stars;

import java.util.ArrayList;

public class Index implements Selectable {
	private String course;
	private ArrayList<Timing> timings;
	private int vacancy;
	private ArrayList<Student> waitList;
	private ArrayList<Student> confirmedList;
	private int indexNumber;

	public Index(String course, ArrayList<Timing> timings, int vacancy, ArrayList<Student> waitList,
			ArrayList<Student> confirmedList, int indexNumber) {
		this.course = course;
		this.timings = timings;
		this.vacancy = vacancy;
		this.waitList = waitList;
		this.confirmedList = confirmedList;
		this.indexNumber = indexNumber;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
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