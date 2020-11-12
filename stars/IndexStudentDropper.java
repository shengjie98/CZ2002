package stars;

import java.util.ArrayList;

public class IndexStudentDropper {
    public boolean dropStudent(Index index, StudentRegisteredCourses studentRegisteredCourses) {
        Student student = studentRegisteredCourses.getOwner();
        ArrayList<Student> confirmed = index.getConfirmedList();
        ArrayList<Student> wait = index.getWaitList();
        if (confirmed.contains(student)){
            index.dropStudentFromConfirmedList(student);
            studentRegisteredCourses.removeFromConfirmedIndexArray(index);
            if (wait.size() > 0) {
                Student newStudent = index.dequeueStudent();
                newStudent.notify(index);
                newStudent.moveToConfirmed(index);
            }
            return true;
        } else {
            index.dropStudentFromWaitList(student);
            studentRegisteredCourses.removeFromWaitlistedIndexArray(index);
            return true;
        }
    }
}
