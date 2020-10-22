package stars;

import java.util.ArrayList;

/**
 * Stores and retrieves student data
 */
public class Student {

    private String studentID;
    private String studentName;
    private String nationality;
    private String gender;
    private String degree;
    private StudentRegisteredCourses registeredCourses;

    // Constructor

    public Student(String studentName, String nationality, String gender, String studentID, String degree) {
		// TODO Auto-generated constructor stub
        this.studentName = studentName;
        this.nationality = nationality;
        this.gender = gender;
        this.studentID = studentID;
        this.degree = degree;
        registeredCourses = new StudentRegisteredCourses(this);
        
    }
    

    public boolean addIndex(Index index) {
        return registeredCourses.addIndex(index);;
    }

    public boolean dropIndex(Index index) {
        return registeredCourses.dropIndex(index);
    }

    public ArrayList<Index> getRegisteredIndex() {
        return registeredCourses.getIndexList();
    }

    public boolean changeIndex(Index oldIndex, Index newIndex){
        return registeredCourses.changeIndex(oldIndex, newIndex);
    } 

	/**
     * Retrieves student identification number
     * @return studentID
     */
    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getNationality() {
        return nationality;
    }
    public String getGender() {
        return gender;
    }
    public String getDegree() {
        return degree;
    }

}
