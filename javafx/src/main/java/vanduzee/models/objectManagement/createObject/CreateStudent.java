package vanduzee.models.objectManagement.createObject;

import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class CreateStudent {
    public static void newStudent(String name, int id) {
        MasterList master = MasterList.getInstance();
        Student newStudent = new Student(name, id);
        master.addMasterStudent(newStudent);

        if (master.getMasterAssignments() != null && !master.getMasterAssignments().isEmpty()) {
            for (Assignment assignment : master.getMasterAssignments()) {
                Assignment newAssignment = new Assignment(assignment);
                newStudent.addAssignment(newAssignment);
            }
        }

        if (master.getMasterTests() != null && !master.getMasterTests().isEmpty()) {
            for (Test test : master.getMasterTests()) {
                Test newTest = new Test(test);
                newStudent.addTest(newTest);
            }
        }
    }
}
