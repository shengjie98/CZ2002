package stars;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Stores and retrieves student data
 */
public class Student implements Selectable, Serializable{

    private String studentID;
    private String studentName;
    private String nationality;
    private String gender;
    private String degree;
    private StudentRegisteredCourses registeredCourses;
    private String email;

    // Constructor

    public Student(String studentName, String nationality, String gender, String studentID, String degree, String email) {
		// TODO Auto-generated constructor stub
        this.studentName = studentName;
        this.nationality = nationality;
        this.gender = gender;
        this.studentID = studentID;
        this.degree = degree;
        this.email = email;
        registeredCourses = new StudentRegisteredCourses(this);
        
    }
    

    public boolean addIndex(Index index) {
        return registeredCourses.addIndex(index);
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

    public boolean checkChangeIndex(Index myIndex, Index friendIndex) {
        return registeredCourses.checkChangeIndex(myIndex, friendIndex);
    }

    public void swopPlaces(Index friendIndex, Student friend) {
        registeredCourses.swopPlaces(friendIndex, friend);
    }
    
    public String print() {
        return this.studentName;
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

    public String getEmail() {
        return email;
    }

}
