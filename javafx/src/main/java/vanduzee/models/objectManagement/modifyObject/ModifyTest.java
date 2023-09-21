package vanduzee.models.objectManagement.modifyObject;

import java.time.LocalDate;

import vanduzee.models.objectManagement.objectUtils.Update;
import vanduzee.models.utility.VariableConversions;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class ModifyTest {
    public static String stringName(String newName) {
        return "\nNew Test Name: " + newName;
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

    public static void updateMasterTest(Test test, String newName, double newTotal, LocalDate newDate,
            double newWeight) {

        String oldName = test.getName();
        test.setName(newName);
        test.setTotal(newTotal);
        test.setDateDue(newDate);
        test.setWeight(newWeight);

        MasterList master = MasterList.getInstance();
        if (master.getMasterStudents() != null || !master.getMasterStudents().isEmpty()) {
            updateStudentCopies(test, oldName, master);
        }
    }

    private static void updateStudentCopies(Test masterTest, String oldName, MasterList master) {

        for (Student student : master.getMasterStudents()) {
            for (Test studentCopy : student.getTests()) {
                if (studentCopy.getName().equals(oldName)) {
                    studentCopy.setName(masterTest.getName());
                    studentCopy.setTotal(masterTest.getTotal());
                    studentCopy.setDateDue(masterTest.getDateDue());
                    studentCopy.setWeight(masterTest.getWeight());
                    Update.uTest(studentCopy);
                }
            }
        }
    }
}
