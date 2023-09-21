package vanduzee.models.tableFormatting;

import java.util.ArrayList;

import vanduzee.models.utility.VariableConversions;
import vanduzee.objects.Assignment;
import vanduzee.objects.Test;

public class StudentTable {
    public static String formatName(String name) {
        return name;
    }

    public static String formatId(Integer id) {
        return VariableConversions.intToString(id);
    }

    public static String formatAssignments(ArrayList<Assignment> assignments) {
        if (assignments == null || assignments.isEmpty()) {
            return "-";
        }

        int submitCount = 0;
        for (Assignment assignment : assignments) {
            if (assignment.getScore() != -1) {
                submitCount++;
            }
        }

        return VariableConversions.intToString(submitCount) + " / "
                + VariableConversions.intToString(assignments.size());
    }

    public static String formatTests(ArrayList<Test> tests) {
        if (tests == null || tests.isEmpty()) {
            return "-";
        }

        int submitCount = 0;

        for (Test test : tests) {
            if (test.getScore() != -1) {
                submitCount++;
            }
        }

        return VariableConversions.intToString(submitCount) + " / " + VariableConversions.intToString(tests.size());
    }

    public static String formatTotalMark(double totalMark) {
        if (totalMark == -1) {
            return "-";
        }
        return VariableConversions.doubleToString(totalMark * 100) + "%";
    }

    public static String formatTotalTest(double totalTest) {
        if (totalTest == -1) {
            return "-";
        }
        return VariableConversions.doubleToString(totalTest) + "%";
    }
}
