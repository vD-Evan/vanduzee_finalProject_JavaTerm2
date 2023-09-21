package vanduzee.models.utility;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VariableConversions {
    public static String doubleToString(double source, int sigDigits) {
        String format = "%." + sigDigits + "f";
        return String.format(format, source);
    }

    public static String doubleToString(double source) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(source);
    }

    public static String localDateToString(LocalDate source) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd");
        String formattedDate = pattern.format(source);
        return formattedDate;
    }

    public static String booleanToString(boolean source) {
        if (source == true) {
            return "Valid";
        } else {
            return "Invalid";
        }
    }

    public static String intToString(int source) {
        return String.valueOf(source);
    }
}
