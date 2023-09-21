package vanduzee.models.tableFormatting;

import vanduzee.models.utility.VariableConversions;

public class AssignmentTable extends AssessmentTable {
    public static String formatScore(double score, int daysLate) {
        if (score == -1) {
            return "-";
        }

        String result = VariableConversions.doubleToString(score);
        if (daysLate >= 1 && daysLate <= 3) {
            result += " -" + Integer.toString(daysLate) + "0%";
        } else if (daysLate >= 4) {
            result += " -100%";
        }

        return result;
    }

    public static String formatPercent(double score, double total, int daysLate) {
        if (score == -1) {
            return "-";
        }

        double percent = score / total * 100.0;
        if (daysLate >= 1 && daysLate <= 3) {
            percent -= (percent * (0.1 * daysLate));
        } else if (daysLate >= 4) {
            percent = 0;
        }

        return VariableConversions.doubleToString(percent) + "%";
    }
}
