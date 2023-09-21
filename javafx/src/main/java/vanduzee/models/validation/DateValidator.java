package vanduzee.models.validation;

import java.time.LocalDate;

import vanduzee.models.utility.VariableConversions;

public class DateValidator {
    public static boolean check(LocalDate date) {
        LocalDate earliest = LocalDate.of(2023, 9, 5);
        LocalDate latest = LocalDate.of(2023, 12, 22);

        String stringEarly = VariableConversions.localDateToString(earliest);
        String stringLate = VariableConversions.localDateToString(latest);

        String header = "Date Out of Bounds";
        String content = "Please enter a date between " + stringEarly + " and " + stringLate + " of 2023.";
        if (date.isAfter(latest)) {
            DisplayMessage.error(header, content);
            return false;
        }
        if (date.isBefore(earliest)) {
            DisplayMessage.error(header, content);
            return false;
        }
        return true;
    }
}
