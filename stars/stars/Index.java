package stars;


public class Index{
    private String course;
    private Timing[] timings;
    private int vacancy;
    private Student[] waitList;
    private Student[] confirmedList;
    private int indexNumber;
    
    public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Timing[] getTimings() {
		return timings;
	}
	public void setTimings(Timing[] timings) {
		this.timings = timings;
	}
	public int getVacancy() {
		return vacancy;
	}
	public void setVacancy(int vacancy) {
		this.vacancy = vacancy;
	}
	public Student[] getWaitList() {
		return waitList;
	}
	public void setWaitList(Student[] waitList) {
		this.waitList = waitList;
	}
	public Student[] getConfirmedList() {
		return confirmedList;
	}
	public void setConfirmedList(Student[] confirmedList) {
		this.confirmedList = confirmedList;
	}
	public int getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(int indexNumber) {
		this.indexNumber = indexNumber;
	}
    
}