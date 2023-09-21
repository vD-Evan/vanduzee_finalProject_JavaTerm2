package vanduzee.objects;

import java.io.Serializable;
import java.util.ArrayList;

public class MasterList implements Serializable {
    private ArrayList<Test> masterTests;
    private ArrayList<Student> masterStudents;
    private ArrayList<Assignment> masterAssignments;
    private static MasterList instance;

    public MasterList() {
        masterTests = new ArrayList<>();
        masterStudents = new ArrayList<>();
        masterAssignments = new ArrayList<>();
    }

    public static MasterList getInstance() {
        if (instance == null) {
            instance = new MasterList();
        }
        return instance;
    }

    public static void setInstance(MasterList newInstance) {
        instance = newInstance;
    }

    public ArrayList<Test> getMasterTests() {
        return masterTests;
    }

    public void addMasterTest(Test newTest) {
        masterTests.add(newTest);
    }

    public void removeMasterTest(Test oldTest) {
        masterTests.remove(oldTest);
    }

    public void clearTests() {
        masterTests.clear();
    }

    public ArrayList<Student> getMasterStudents() {
        return masterStudents;
    }

    public void addMasterStudent(Student newStudent) {
        masterStudents.add(newStudent);
    }

    public void removeMasterStudent(Student oldStudent) {
        masterStudents.remove(oldStudent);
    }

    public void clearStudents() {
        masterStudents.clear();
    }

    public ArrayList<Assignment> getMasterAssignments() {
        return masterAssignments;
    }

    public void addMasterAssignment(Assignment newAssignment) {
        masterAssignments.add(newAssignment);
    }

    public void removeMasterAssignment(Assignment oldAssignment) {
        masterAssignments.remove(oldAssignment);
    }

    public void clearAssignments() {
        masterAssignments.clear();
    }
}
