package stars.boundary;

import stars.controller.*;
import stars.entity.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AdminUI extends SelectUI {
    private AdminController adminController;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public AdminUI() {
        adminController = new AdminController();
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in admin display menu");
        do {
            System.out.println("1. Edit student access period");
            System.out.println("2. Add student");
            System.out.println("3. Add course");
            System.out.println("4. Update course");
            System.out.println("5. Check availability slot for an index");
            System.out.println("6. Print student list by index number");
            System.out.println("7. Print student list by course");
            System.out.println("8. Print all students in database");
            System.out.println("9. Print all courses in database");
            System.out.print("Option: ");
            i = sc.nextInt();
            switch (i) {
                case 1:
                    editStudentAccess();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    editCourseInformation();
                    break;
                case 5:
                    checkVacancy();
                    break;
                case 6:
                    printStudentListByIndex();
                    break;
                case 7:
                    printStudentListByCourse();
                    break;
                case 8:
                    getallstudents();
                    break;
                case 9:
                    getallcourses();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 10);
        adminController.save();
        return;
    }

    private void editStudentAccess() {
        ArrayList<Student> studentList = this.adminController.getStudentList();
        Student selectedStudent = (Student) select(studentList);
        Scanner sc = new Scanner(System.in);
        LocalDateTime start, end;
        while (true) {
            try {
                System.out.print("  a. Access Period Start (DD/MM/YYYY hh:mm): ");
                start = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        while (true) {
            try {
                System.out.print("  b. Access Period End (DD/MM/YYYY hh:mm): ");
                end = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                if (end.isAfter(start)) {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        adminController.editAccess(selectedStudent.getStudentID(), start, end);
    }

    private void addStudent() {
        Scanner sc = new Scanner(System.in);
        String studentName, studentID, password, nationality, gender, degree, email;
        LocalDateTime start, end;
        System.out.println("Add a Student");
        System.out.print("  a. Student Name: ");
        studentName = sc.nextLine();
        System.out.print("  b. Student ID: ");
        studentID = sc.nextLine();
        System.out.print("  c. Password: ");
        password = sc.nextLine();
        System.out.print("  d. Nationality: ");
        nationality = sc.nextLine();
        System.out.print("  e. Gender: ");
        gender = sc.nextLine();
        System.out.print("  f. Degree: ");
        degree = sc.nextLine();
        System.out.print("  g. Email: ");
        email = sc.nextLine();
        // need to change to local time object here
        while (true) {
            try {
                System.out.print("  h. Access Period Start (DD/MM/YYYY hh:mm): ");
                start = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        while (true) {
            try {
                System.out.print("  i. Access Period End (DD/MM/YYYY hh:mm): ");
                end = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }

        // admin controller.add student(studentName, nationality, gender, studentID,
        // degree, email)
        adminController.addStudent(studentName, nationality, gender, studentID, degree, email, password, start, end);
    }

    private void addCourse() {
        System.out.println("Enter Course Name:");
        Scanner courseName = new Scanner(System.in);
        String newCourseName = courseName.nextLine();
        System.out.println("Enter Course ID:");
        Scanner courseID = new Scanner(System.in);
        String newCourseID = courseID.nextLine();
        System.out.println("Enter number of AUs:");
        Scanner au = new Scanner(System.in);
        int newAU = au.nextInt();
        System.out.println("Enter School that course belongs to:");
        Scanner school = new Scanner(System.in);
        String newSchool = school.nextLine();

        Course newCourse = adminController.createCourse(newCourseID, newAU, newSchool, newCourseName);

        System.out.println("How many Indexes do you want to add?");
        Scanner scannerNumOfIndexes = new Scanner(System.in);
        int numOfIndexes = scannerNumOfIndexes.nextInt();
        for (int a = 0; a < numOfIndexes; a++) {
            System.out.println("Input Vacancy of Course: ");
            Scanner vacancy = new Scanner(System.in);
            int newVacancy = vacancy.nextInt();
            System.out.println("Input Index Number: ");
            Scanner indexNumber = new Scanner(System.in);
            int newIndexNumber = indexNumber.nextInt();
            Index newIndex = adminController.createIndex(newCourse, newVacancy, newIndexNumber);
            System.out.println("Input Timings: ");
            while (true) {
                // get the type
                String type;
                Timing.Type newType = Timing.Type.LEC; // !!This is not supposed to be the default; change this
                                                       // before submitting
                do {
                    System.out.println("Type(LEC/TUT/LAB): ");
                    Scanner t = new Scanner(System.in);
                    type = t.nextLine();
                } while ((!type.equals("LEC")) && (!type.equals("TUT")) && (!type.equals("LAB")));
                switch (type) {
                    case "LEC":
                        newType = Timing.Type.LEC;
                    case "TUT":
                        newType = Timing.Type.TUT;
                    case "LAB":
                        newType = Timing.Type.LAB;
                }
                // get the day of the timing
                String day;
                Timing.Day newDay = Timing.Day.MON; // !!This is not supposed to be the default; change this before
                                                    // submitting
                do {
                    System.out.println("Day(MON/TUE/WED/THU/FRI): ");
                    Scanner d = new Scanner(System.in);
                    day = d.nextLine();
                } while ((!day.equals("MON")) && (!day.equals("TUE")) && (!day.equals("WED")) && (!day.equals("THU"))
                        && (!day.equals("FRI")));
                // enum part??
                switch (day) {
                    case "MON":
                        newDay = Timing.Day.MON;
                    case "TUE":
                        newDay = Timing.Day.TUE;
                    case "WED":
                        newDay = Timing.Day.WED;
                    case "THU":
                        newDay = Timing.Day.THU;
                    case "FRI":
                        newDay = Timing.Day.FRI;
                }

                // get the start time
                System.out.println("Start Time: ");
                Scanner startTime = new Scanner(System.in);
                LocalTime start = LocalTime.parse(startTime.nextLine(), timeFormatter);
                // get the end time
                System.out.println("End Time: ");
                Scanner endTime = new Scanner(System.in);
                LocalTime end = LocalTime.parse(endTime.nextLine(), timeFormatter);

                // yet to convert to enum so the constructor is throwing an error
                Timing newTiming = adminController.createTiming(newDay, newType, start, end);
                if (adminController.addTiming(newIndex, newTiming)) {
                    System.out.println("Timing Added!");
                } else {
                    System.out.println("Timing could not be added! There was a clash in timing.");
                }
                System.out.println("Any more Timings? Y/N");
                Scanner yesOrNo = new Scanner(System.in);
                if ((yesOrNo.nextLine()).equals("N")) {
                    break;
                }
            }
            adminController.addIndex(newCourse, newIndex);
            System.out.println("Index added!");
        }
        adminController.addCourse(newCourse);
    }

    private void editCourseInformation() {
        System.out.println("Edit Course Information");

        // get list of courses from the course database
        ArrayList<Course> courseList;
        courseList = adminController.getCourseList();
        // this will print out the list of courses and allow the user to select
        // the course they want to edit
        Course selectedCourse = (Course) select(courseList);

        // get user to choose the course information to edit
        System.out.println("Which part of the course do you want to edit?:");
        System.out.println("1: Course Code");
        System.out.println("2: Course Name");
        System.out.println("3: School");
        System.out.println("4: Add an Index");
        System.out.println("5: Drop Index Number");
        System.out.println("6: Change Index ID");
        System.out.println("7: Change vacancy of the Index");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {
            case 1:
                /**
                 * Change course ID attribute
                 */
                System.out.println("Enter new Course ID: ");
                Scanner newCourseID = new Scanner(System.in);
                selectedCourse.setCourseID(newCourseID.nextLine());
                break;
            case 2:
                /**
                 * Change school attribute
                 */
                System.out.println("Enter new Course name: ");
                Scanner newSchool = new Scanner(System.in);
                selectedCourse.setCourseName(newSchool.nextLine());
                break;
            case 3:
                /**
                 * Change school attribute
                 */
                System.out.println("Enter new School name: ");
                Scanner newSchool = new Scanner(System.in);
                selectedCourse.setSchool(newSchool.nextLine());
                break;
            case 4:
                /**
                 * Add a new index into an existing course
                 */
                // get the attributes
                System.out.println("Input Vacancy of Course: ");
                Scanner vacancy = new Scanner(System.in);
                int newVacancy = vacancy.nextInt();
                System.out.println("Input Index Number: ");
                Scanner indexNumber = new Scanner(System.in);
                int newIndexNumber = indexNumber.nextInt();
                Index newIndex = adminController.createIndex(selectedCourse, newVacancy, newIndexNumber);
                System.out.println("Input Timings: ");
                while (true) {
                    // get the type
                    String type;
                    Timing.Type newType = Timing.Type.LEC; // !!This is not supposed to be the default; change this
                                                           // before submitting
                    do {
                        System.out.println("Type(LEC/TUT/LAB): ");
                        Scanner t = new Scanner(System.in);
                        type = t.nextLine();
                    } while ((!type.equals("LEC")) && (!type.equals("TUT")) && (!type.equals("LAB")));
                    switch (type) {
                        case "LEC":
                            newType = Timing.Type.LEC;
                        case "TUT":
                            newType = Timing.Type.TUT;
                        case "LAB":
                            newType = Timing.Type.LAB;
                    }
                    // get the day of the timing
                    String day;
                    Timing.Day newDay = Timing.Day.MON; // !!This is not supposed to be the default; change this before
                                                        // submitting
                    do {
                        System.out.println("Day(MON/TUE/WED/THU/FRI): ");
                        Scanner d = new Scanner(System.in);
                        day = d.nextLine();
                    } while ((!day.equals("MON")) && (!day.equals("TUE")) && (!day.equals("WED"))
                            && (!day.equals("THU")) && (!day.equals("FRI")));
                    // enum part??
                    switch (day) {
                        case "MON":
                            newDay = Timing.Day.MON;
                        case "TUE":
                            newDay = Timing.Day.TUE;
                        case "WED":
                            newDay = Timing.Day.WED;
                        case "THU":
                            newDay = Timing.Day.THU;
                        case "FRI":
                            newDay = Timing.Day.FRI;
                    }

                    // get the start time
                    System.out.println("Start Time: ");
                    Scanner startTime = new Scanner(System.in);
                    LocalTime start = LocalTime.parse(startTime.nextLine(), timeFormatter);
                    // get the end time
                    System.out.println("End Time: ");
                    Scanner endTime = new Scanner(System.in);
                    LocalTime end = LocalTime.parse(endTime.nextLine(), timeFormatter);

                    // yet to convert to enum so the constructor is throwing an error
                    Timing newTiming = adminController.createTiming(newDay, newType, start, end);
                    if (adminController.addTiming(newIndex, newTiming)) {
                        System.out.println("Timing Added!");
                    } else {
                        System.out.println("Timing could not be added! There was a clash in timing.");
                    }
                    System.out.println("Any more Timings? Y/N");
                    Scanner yesOrNo = new Scanner(System.in);
                    if ((yesOrNo.nextLine()).equals("N")) {
                        break;
                    }
                }
                if (adminController.addIndex(selectedCourse, newIndex)) {
                    System.out.println("Index Added!");
                } else {
                    System.out.println("Index Could not be added! Duplicate Index ID.");
                }
                break;
            case 5:
                /**
                 * Drop Index
                 */
                // get the list of indexes from the course object
                ArrayList<Index> indexList = selectedCourse.getIndexList();
                // this will print out the list of indexes and allow the user to select
                // the index that they want to drop; The admin will only
                // be allowed to drop it if it has no students
                Index selectedIndex = (Index) select(indexList);
                if (adminController.dropIndex(selectedCourse, selectedIndex)) {
                    System.out.println("Index Dropped.");
                } else {
                    System.out.println("Unable to drop Index. Students are already in the Index!");
                }
                break;
            case 6:
                /**
                 * Change Index ID
                 */
                // get the list of indexes from the course object
                ArrayList<Index> indexList = selectedCourse.getIndexList();
                // this will print out the list of indexes and allow the user to select
                // the index that they want to drop; The admin will only
                // be allowed to drop it if it has no students
                Index selectedIndex = (Index) select(indexList);
                if (adminController.dropIndex(selectedCourse, selectedIndex)) {
                    System.out.println("Index Dropped.");
                } else {
                    System.out.println("Unable to drop Index. Students are already in the Index!");
                }
                break;
            case 7:
                /**
                 * Change vacancy limit attribute of course
                 */
                ArrayList<Index> indexList2 = selectedCourse.getIndexList();
                // Get the user to choose the index
                Index selectedIndex2 = (Index) select(indexList2);
                // Get the user to input the vacancy which must be
                // greater than the current length of the list of
                // students
                int newVacancy2;
                do {
                    System.out.print("New Vacancy of Index: ");
                    Scanner newVanacyIn = new Scanner(System.in);
                    // !! add while loop to force user to enter positive value
                    newVacancy2 = newVanacyIn.nextInt();
                    if (newVacancy2 < 0) {
                        System.out.println("Please try again! Your new vacancy cannot be negative!");
                    }
                } while (newVacancy2 < 0);
                if (adminController.setVacancyLimit(selectedIndex2, newVacancy2) == false) {
                    System.out.println("Unable to set new vacancy. Students will be kicked out of the list!");
                }
                break;
            default:
                break;
        }
    }

    private void checkVacancy() {
        // get list of courses from the course database
        ArrayList<Course> courseList;
        courseList = adminController.getCourseList();
        // this will print out the list of courses and allow the user to select
        // the course they want to edit
        Course selectedCourse = (Course) select(courseList);
        ArrayList<Index> indexList = selectedCourse.getIndexList();
        // Get the user to choose the index
        Index selectedIndex = (Index) select(indexList);
        System.out.printf("Vacancy is: %d\n", selectedIndex.getVacancy());
    }

    private void printStudentListByIndex() {
        // get list of courses from the course database
        ArrayList<Course> courseList;
        courseList = adminController.getCourseList();
        // this will print out the list of courses and allow the user to select
        // the course they want to edit
        Course selectedCourse = (Course) select(courseList);
        ArrayList<Index> indexList = selectedCourse.getIndexList();
        // Get the user to choose the index
        Index selectedIndex = (Index) select(indexList);
        ArrayList<Student> confirmedList = selectedIndex.getConfirmedList();
        for (int i = 0; i < confirmedList.size(); i++) {
            System.out.printf("%s\n", (confirmedList.get(i)).print());
        }
    }

    private void printStudentListByCourse() {
        // get list of courses from the course database
        ArrayList<Course> courseList;
        courseList = adminController.getCourseList();
        // this will print out the list of courses and allow the user to select
        // the course they want to edit
        Course selectedCourse = (Course) select(courseList);
        ArrayList<Index> indexList = selectedCourse.getIndexList();

        for (Index eachIndex : indexList) {
            ArrayList<Student> confirmedList = eachIndex.getConfirmedList();
            for (Student eachStudent : confirmedList) {
                System.out.printf("%s\n", eachStudent.print());
            }
        }
    }

    private void getallstudents() {
        ArrayList<Student> ls = adminController.getStudentList();
        for (Student smth : ls) {
            System.out.printf("%s\n", smth.print());
        }
    }

    private void getallcourses() {
        ArrayList<Course> ls = adminController.getCourseList();
        for (Course another : ls) {
            System.out.printf("%s\n", another.print());
        }
    }
}
