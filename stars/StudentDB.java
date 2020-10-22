import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * handles student data
 */
public class StudentDB implements Database{

    private List<Student> studentList = new ArrayList<Student>();

    //GET METHODS

    /**
     * Retrieve the list of all students
     * @return student object list
     */
    public List<Student> getStudentList(){
        return studentList;
    }

    //SET METHODS

    /**
     * add a student into the student object list
     * @param studentName student name
     */
    public void addStudent (String studentName, String nationality, String gender, String studentId, String degree, String email) {
        studentList.add(new Student(studentName, nationality, gender, studentId, degree, email));
    }

	public void loadInformation() {
		 List<String> students = new ArrayList<String>();
	     Scanner sc;
	     String line;
	     String delimiter = "[ ]+";

	     //scan from students.txt into List students
	     try{
	            //scan from courses.txt line by line into String line
	            //split each line into token based on spaces and store into List courses
	            sc = new Scanner(new File("students.txt"));
	            while(sc.hasNextLine()){
	                line = sc.nextLine();
	                students.add(line.split(delimiter));
	            }
	            for(int i = 0; i<students.size(); i++){

	                //initialise and set values for each array of strings in List courses
					String index;
					String gender;
					String nationaity;
	                String student;
					String school = null;

	                //index is the first string in the array
	                index = students.get(i)[0];

	                //gender is the second string in the array
	                gender = students.get(i)[1];
	                
	                //nationality is the third string in the array  
	                nationaity = students.get(i)[2];
	                
	                //school is the fourth string in the array
	                school = students.get(i)[3];

	                //student is the fifth in the array
	                student = Integer.parseInt(students.get(i)[4]);
	            }

	        }catch(IOException e){
	            e.printStackTrace();
	        }
	     	
	}

	@Override
	public void saveInformation() {
		// TODO Auto-generated method stub
		
		
	}
	

}
