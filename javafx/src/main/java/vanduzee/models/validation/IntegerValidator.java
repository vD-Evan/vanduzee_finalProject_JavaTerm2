package vanduzee.models.validation;

import vanduzee.objects.MasterList;
import vanduzee.objects.Student;

public class IntegerValidator {
    private static boolean typeCheck(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            String header = "Error: Number Format";
            String content = "Please enter only numeric characters.";
            DisplayMessage.error(header, content);
            return false;
        }
        return true;
    }

    public static boolean checkId(String number) {
        if (!typeCheck(number)) {
            return false;
        }

        if (number.length() != 9) {
            String header = "Error: ID Length";
            String content = "Student IDs must be 9 digits long.";
            DisplayMessage.error(header, content);
            return false;
        }

        int studentId = Integer.parseInt(number);
        MasterList master = MasterList.getInstance();

        if (master.getMasterStudents() != null && !master.getMasterStudents().isEmpty()) {
            for (Student student : master.getMasterStudents()) {
                if (studentId == student.getId()) {
                    String header = "Error: Duplicate ID";
                    String content = "Student ID matches an existing student's ID. Please provide a unique ID.";
                    DisplayMessage.error(header, content);
                    return false;
                }
            }
        }

        return true;
    }
}
