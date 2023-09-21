package vanduzee.models.objectManagement.modifyObject;

import java.time.LocalDate;

import vanduzee.models.objectManagement.objectUtils.Update;
import vanduzee.models.utility.VariableConversions;
import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;

public class ModifyAssignment {
    public static String stringName(String newName) {
        return "\nNew Assignment Name: " + newName;
    }

    public static String stringTotal(String newTotal) {
        return "\nNew Total: " + newTotal;
    }

    public static double doubleTotal(String newTotal) {
        return Double.parseDouble(newTotal);
    }

    public static String stringDate(LocalDate newDate) {
        return "\nNew Date: " + VariableConversions.localDateToString(newDate) + ", 2023";
    }

    public static LocalDate localDateDue(LocalDate newDate) {
        return newDate;
    }

    public static String stringWeight(String newWeight) {
        return "\nNew Weight: " + newWeight + "%";
    }

    public static double doubleWeight(String newWeight) {
        return Double.parseDouble(newWeight) / 100;
    }

    public static void updateMasterAssignment(Assignment assignment, String newName, double newTotal, LocalDate newDate,
            double newWeight) {

        String oldName = assignment.getName();
        assignment.setName(newName);
        assignment.setTotal(newTotal);
        assignment.setDateDue(newDate);
        assignment.setWeight(newWeight);

        MasterList master = MasterList.getInstance();
        if (master.getMasterStudents() != null || !master.getMasterStudents().isEmpty()) {
            updateStudentCopies(assignment, oldName, master);
        }
    }

    private static void updateStudentCopies(Assignment masterAssignment, String oldName, MasterList master) {

        for (Student student : master.getMasterStudents()) {
            for (Assignment studentCopy : student.getAssignments()) {
                if (studentCopy.getName().equals(oldName)) {
                    studentCopy.setName(masterAssignment.getName());
                    studentCopy.setTotal(masterAssignment.getTotal());
                    studentCopy.setDateDue(masterAssignment.getDateDue());
                    studentCopy.setWeight(masterAssignment.getWeight());
                    Update.uAssignment(studentCopy);
                }
            }
        }
    }
}
