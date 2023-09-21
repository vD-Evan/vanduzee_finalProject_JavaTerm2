package vanduzee.models.objectManagement.createObject;

import java.time.LocalDate;

import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class CreateTest {
    public static void newTest(String newName, String newTotal, LocalDate newDate, String newWeight) {
        String name = newName;
        double total = Double.parseDouble(newTotal);
        LocalDate date = newDate;
        double weight = Double.parseDouble(newWeight) / 100;

        MasterList master = MasterList.getInstance();
        Test newTest = new Test(name, total, weight, date);
        master.addMasterTest(newTest);

        if (master.getMasterStudents() == null || master.getMasterStudents().isEmpty()) {
            return;
        }

        for (Student student : master.getMasterStudents()) {
            Test studentCopy = new Test(newTest);
            student.addTest(studentCopy);
        }
    }
}
