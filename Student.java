/**
 * Stores and retrieves student data
 */
public class Student {

    private String studentID;
    private String studentName;
    private String nationality;
    private String gender;
    private String email;
    private String degree;
    
    // Constructor

    public Student(String studentName, String nationality, String gender, String studentID, String degree, String email) {
		// TODO Auto-generated constructor stub
    	this.studentName = studentName;
    	this.nationality = nationality;
    	this.gender = gender;
    	this.studentID = studentID;
    	this.degree = degree;
    	this.email = email;
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