package stars.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

/**
 * handles student data
 */
public class StudentDB implements Database {
	private ArrayList<Student> studentList = new ArrayList<Student>();
    private final String STUDENT_DATABASE_FILE = "stars/students.ser";
	// File file = new File("stars/students.ser");

	// format to show the serialisation 
	// students.add(new Student("Yu Xuan", "Singapore", "F", "U1920126K", "SCSE", "kohy0083@e.ntu.edu.sg"));
	// students.add(new Student("Hiok Hian", "Singapore", "M", "U1920000K", "SCSE", "a@hotmail.com"));
	// students.add(new Student("Sheng Jie", "Singapore", "M", "U1920001K", "SCSE", "a@gmail.com"));
	// students.add(new Student("Patria", "Singapore", "F", "U1920002K", "SCSE", "abc@gmail.com"));

	// serialising it
	// FileOutputStream fo = new FileOutputStream(file);
	// ObjectOutputStream output = new ObjectOutputStream(fo);
	// for (Student s: students) {
	// 	output.writeObject(s);
	// }
	// output.close();
	// fo.close();

	// deserialise it 
	// FileInputStream fi = new FileInputStream(file);
	// ObjectInputStream input = new ObjectInputStream(fi);
	// ArrayList<Student> students2 = new ArrayList<Student>();
		
	// try {
	// 	while (true) {
	// 		Student s = (Student)input.readObject();
	// 		students2.add(s);
	// 	} 
	// } catch (EOFException ex) {
	// }


	// private final String STUDENT_DATABASE_FILE = "stars/students.ser";

	public StudentDB() {
		this.loadInformation();
	}

	// GET METHODS

	/**
	 * Retrieve the list of all students
	 * 
	 * @return student object list
	 */
	public ArrayList getList() {
		return this.studentList;
	}

	// SET METHODS

	/**
	 * add a student into the student object list
	 * 
	 * @param studentName student name
	 */
	public void addItem(Object student) {
		studentList.add((Student)student);
	}

	public Student findStudent(String studentID) {
		for (Student student : this.studentList) {
			if ((student.getStudentID()).equals(studentID)) {
				return student;
			}
		}
		return null;
	}

	// sj stuff feel free to change @yx
	public void loadInformation() {
		try {
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(STUDENT_DATABASE_FILE));
			this.studentList = (ArrayList<Student>) inputStream.readObject();
			inputStream.close();
		} catch (IOException e) {
			// System.out.println("IOException");
			this.studentList = new ArrayList<Student>();
		} catch (ClassNotFoundException e) {
			// System.out.println("Class not found");
			this.studentList = new ArrayList<Student>();
		}
	}

	// sj stuff feel free to change @yx
	public void saveInformation() {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(STUDENT_DATABASE_FILE));
			outputStream.writeObject(this.studentList);
			outputStream.close();
		} catch (IOException e) {
			System.out.println("Class not found hi");
		}
	}

	// public void loadInformation() {
	// List<String> students = new ArrayList<String>();
	// Scanner sc;
	// String line;
	// String delimiter = "[ ]+";

	// //scan from students.txt into List students
	// try{
	// //scan from courses.txt line by line into String line
	// //split each line into token based on spaces and store into List courses
	// sc = new Scanner(new File("students.txt"));
	// while(sc.hasNextLine()){
	// line = sc.nextLine();
	// students.add(line.split(delimiter));
	// }
	// for(int i = 0; i<students.size(); i++){

	// //initialise and set values for each array of strings in List courses
	// String index;
	// String gender;
	// String nationaity;
	// String student;
	// String school = null;

	// //index is the first string in the array
	// index = students.get(i)[0];

	// //gender is the second string in the array
	// gender = students.get(i)[1];

	// //nationality is the third string in the array
	// nationaity = students.get(i)[2];

	// //school is the fourth string in the array
	// school = students.get(i)[3];

	// //student is the fifth in the array
	// student = Integer.parseInt(students.get(i)[4]);
	// }

	// }catch(IOException e){
	// e.printStackTrace();
	// }

	// }

}
