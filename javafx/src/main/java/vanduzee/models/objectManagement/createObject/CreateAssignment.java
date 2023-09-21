package vanduzee.models.objectManagement.createObject;

import java.time.LocalDate;

import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;

public class CreateAssignment {
    public static void newAssignment(String newName, String newTotal, LocalDate newDate, String newWeight) {
        String name = newName;
        double total = Double.parseDouble(newTotal);
        LocalDate date = newDate;
        double weight = Double.parseDouble(newWeight) / 100;

        MasterList master = MasterList.getInstance();
        Assignment newAssignment = new Assignment(name, total, weight, date);
        master.addMasterAssignment(newAssignment);

        if (master.getMasterStudents() == null || master.getMasterStudents().isEmpty()) {
            return;
        }

        for (Student student : master.getMasterStudents()) {
            Assignment studentCopy = new Assignment(newAssignment);
            student.addAssignment(studentCopy);
        }
    }
}
