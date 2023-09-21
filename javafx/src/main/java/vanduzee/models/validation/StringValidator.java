package vanduzee.models.validation;

import vanduzee.objects.Assignment;
/* #endregion */
/* #region imports */
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class StringValidator {
    /* #region student */
    public static boolean checkStudentName(String name) {
        if (name == null || name.length() < 4) {
            String header = "Error: Name Length";
            String content = "Names must have 4 or more characters to be valid.";
            DisplayMessage.error(header, content);
            return false;
        }

        MasterList master = MasterList.getInstance();
        if (master.getMasterStudents() == null || master.getMasterStudents().isEmpty()) {
            return true;
        }

        for (Student student : master.getMasterStudents()) {
            if (student.getName().equals(name)) {
                String header = "Error: Duplicate Name";
                String content = "Name entered matches that of another student. Please enter a unique name.";
                DisplayMessage.error(header, content);
                return false;
            }
        }
        return true;
    }
    /* #endregion */

    /* #region test */
    public static boolean checkTestName(String name) {
        if (name == null) {
            String header = "Error: Missing Name";
            String content = "Please provide a name for your test.";
            DisplayMessage.error(header, content);
            return false;
        }

        MasterList master = MasterList.getInstance();
        if (master.getMasterTests() == null || master.getMasterTests().isEmpty()) {
            return true;
        }

        for (Test test : master.getMasterTests()) {
            if (name.equals(test.getName())) {
                String header = "Error: Duplicate Name";
                String content = "Test name matches an existing test's name. Please provide a unique name.";
                DisplayMessage.error(header, content);
                return false;
            }
        }
        return true;
    }

    public static boolean checkTestName(String name, Test oldTest) {
        if (name == null) {
            String header = "Error: Missing Name";
            String content = "Please provide a name for your test.";
            DisplayMessage.error(header, content);
            return false;
        }

        MasterList master = MasterList.getInstance();
        if (master.getMasterTests() == null || master.getMasterTests().isEmpty()) {
            return true;
        }

        for (Test test : master.getMasterTests()) {
            if (name.equals(test.getName()) && test != oldTest) {
                String header = "Error: Duplicate Name";
                String content = "Test name matches an existing test's name. Please provide a unique name.";
                DisplayMessage.error(header, content);
                return false;
            }
        }
        return true;
    }
    /* #endregion */

    /* #region assignment */
    public static boolean checkAssignmentName(String name) {
        if (name == null) {
            String header = "Error: Missing Name";
            String content = "Please provide a name for your assignment.";
            DisplayMessage.error(header, content);
            return false;
        }

        MasterList master = MasterList.getInstance();
        if (master.getMasterAssignments() == null || master.getMasterAssignments().isEmpty()) {
            return true;
        }

        for (Assignment assignment : master.getMasterAssignments()) {
            if (name.equals(assignment.getName())) {
                String header = "Error: Duplicate Name";
                String content = "Assignment name matches an existing assignment's name. Please provide a unique name.";
                DisplayMessage.error(header, content);
                return false;
            }
        }

        return true;
    }

    public static boolean checkAssignmentName(String name, Assignment oldAssignment) {
        if (name == null) {
            String header = "Error: Missing Name";
            String content = "Please provide a name for your assignment.";
            DisplayMessage.error(header, content);
            return false;
        }

        MasterList master = MasterList.getInstance();
        if (master.getMasterAssignments() == null || master.getMasterAssignments().isEmpty()) {
            return true;
        }

        for (Assignment assignment : master.getMasterAssignments()) {
            if (name.equals(assignment.getName()) && assignment != oldAssignment) {
                String header = "Error: Duplicate Name";
                String content = "Assignment name matches an existing assignment's name. Please provide a unique name.";
                DisplayMessage.error(header, content);
                return false;
            }
        }
        return true;
    }
    /* #endregion */
}
