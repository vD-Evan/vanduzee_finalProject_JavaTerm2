package vanduzee.models.objectManagement.objectUtils;

import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class Remove {
    public static void rmStudent(Student student) {
        MasterList master = MasterList.getInstance();
        master.removeMasterStudent(student);
    }

    public static void rmTest(Test test) {
        String testName = test.getName();

        MasterList master = MasterList.getInstance();
        master.removeMasterTest(test);

        if (master.getMasterStudents() != null || !master.getMasterStudents().isEmpty()) {
            for (Student student : master.getMasterStudents()) {
                for (Test studentCopy : student.getTests()) {
                    if (studentCopy.getName().equals(testName)) {
                        student.removeTest(studentCopy);
                    }
                }
            }
        }
    }

    public static void rmAssignment(Assignment assignment) {
        String assignmentName = assignment.getName();

        MasterList master = MasterList.getInstance();
        master.removeMasterAssignment(assignment);

        if (master.getMasterStudents() != null || !master.getMasterStudents().isEmpty()) {
            for (Student student : master.getMasterStudents()) {
                for (Assignment studentCopy : student.getAssignments()) {
                    if (studentCopy.getName().equals(assignmentName)) {
                        student.removeAssignment(studentCopy);
                    }
                }
            }
        }
    }
}
