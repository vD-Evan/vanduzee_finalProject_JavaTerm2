package vanduzee.models.tableFormatting;

import java.time.LocalDate;

import vanduzee.models.utility.VariableConversions;

public class AssessmentTable {
    public static String formatName(String name) {
        return name;
    }

    public static String formatTotal(double total) {
        return VariableConversions.doubleToString(total);
    }

    public static String formatWeight(double weight) {
        double displayWeight = weight * 100;
        return VariableConversions.doubleToString(displayWeight) + "%";
    }

    public static String formatDate(LocalDate date) {
        LocalDate earliest = LocalDate.of(2023, 9, 5);
        LocalDate latest = LocalDate.of(2023, 12, 22);

        if (date.isBefore(earliest) || date.isAfter(latest)) {
            return "-";
        }

        return VariableConversions.localDateToString(date);
    }
}
