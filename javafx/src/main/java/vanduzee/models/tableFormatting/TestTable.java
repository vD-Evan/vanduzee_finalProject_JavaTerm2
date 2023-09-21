package vanduzee.models.tableFormatting;

import vanduzee.models.utility.VariableConversions;

public class TestTable {
    public static String formatScore(double score, int daysLate) {
        if (score == -1) {
            return "-";
        }

        String result = VariableConversions.doubleToString(score);
        if (daysLate >= 1) {
            result += " -100%";
        }
        return result;
    }

    public static String formatPercent(double score, double total, int daysLate) {
        if (score == -1) {
            return "-";
        }

        double percent = score / total * 100.0;
        if (daysLate >= 1) {
            percent = 0.0;
        }

        return VariableConversions.doubleToString(percent) + "%";
    }
}
