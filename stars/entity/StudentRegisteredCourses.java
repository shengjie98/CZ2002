package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;
import stars.exceptions.AlreadyRegisteredException;
import stars.exceptions.ExceedAUException;
import stars.exceptions.TimetableClashException;

public class StudentRegisteredCourses implements Serializable {
    private ArrayList<Index> waitlistedIndexArray;
    private ArrayList<Index> confirmedIndexArray;
    private Student owner;
    private int AU;

    public StudentRegisteredCourses(Student owner) {
        this.owner = owner;
        waitlistedIndexArray = new ArrayList<Index>();
        confirmedIndexArray = new ArrayList<Index>();
    }

    public ArrayList<Index> getIndexList() {
        ArrayList<Index> temp = (ArrayList<Index>) this.waitlistedIndexArray.clone();
        temp.addAll(this.confirmedIndexArray);
        return temp;
    }

    public ArrayList<Index> getWaitlistedIndexArray() {
        return waitlistedIndexArray;
    }

    public ArrayList<Index> getConfirmedIndexArray() {
        return confirmedIndexArray;
    }

    public int getAU() {
        return AU;
    }

    public void addAU(int au) {
        this.AU += au;
    }

    public void addToConfirmedIndexArray(Index index) {
        confirmedIndexArray.add(index);
    }

    public void addToWaitlistedIndexArray(Index index) {
        waitlistedIndexArray.add(index);
    }

    public void removeFromConfirmedIndexArray(Index index) {
        confirmedIndexArray.remove(index);
    }

    public void removeFromWaitlistedIndexArray(Index index) {
        waitlistedIndexArray.remove(index);
    }

    public void moveToConfirmed(Index index) {
        confirmedIndexArray.add(index);
        waitlistedIndexArray.remove(index);
    }

    public Student getOwner() {
        return owner;
    }

    public boolean addIndex(Index index) throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        IndexStudentAdder indexStudentAdder = new IndexStudentAdder();
        boolean success = indexStudentAdder.addStudent(index, this);
        return success;
    }

    public boolean dropIndex(Index index) {
        IndexStudentDropper indexStudentDropper = new IndexStudentDropper();
        boolean success = indexStudentDropper.dropStudent(index, this);
        return success;
    }

    public boolean changeIndex(Index oldIndex, Index newIndex) throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        TimetableClashChecker clashChecker = new TimetableClashChecker();
        if (oldIndex.getIndexNumber() == newIndex.getIndexNumber()) {
            throw new AlreadyRegisteredException();
        }
        boolean allowed = clashChecker.checkClash(this, newIndex, oldIndex);
        // System.out.println("checkclash result: ");
        // System.out.println(allowed);
        if (allowed) {
            IndexStudentAdder adder = new IndexStudentAdder();
            IndexStudentDropper dropper = new IndexStudentDropper();
            dropper.dropStudent(oldIndex, this);
            // System.out.println("drop result: ");
            // System.out.println(allowed);
            allowed = adder.addStudent(newIndex, this);
            return allowed;
            // System.out.println("add result: ");
            // System.out.println(allowed);
        }
        throw new TimetableClashException();
    }

    public boolean swopPlaces(Index friendIndex, Student friend) throws TimetableClashException, AlreadyRegisteredException {
        IndexStudentSwopper swopper = new IndexStudentSwopper();
        return swopper.swopStudent(friendIndex, friend, this.owner);
    }
}
