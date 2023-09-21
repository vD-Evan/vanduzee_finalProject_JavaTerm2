package vanduzee.models.validation;

import vanduzee.models.utility.VariableConversions;

public class DoubleValidator {
    private static double maxScore = 100.0;
    private static double minScore = 0.0;

    private static boolean typeCheck(String number) {
        try {
            Double.parseDouble(number);
        } catch (NumberFormatException e) {
            String header = "Error: Number Format";
            String content = "Please enter only numeric characters (digits after a decimal are allowed).";
            DisplayMessage.error(header, content);
            return false;
        }
        return true;
    }

    public static boolean checkWeight(String weight) {
        if (!typeCheck(weight)) {
            return false;
        }
        double doubleWeight = Double.parseDouble(weight);
        if (doubleWeight > 100 || doubleWeight < 0) {
            String header = "Error: Weight Out of Bounds";
            String content = "Please enter a number between 0 and 100.";
            DisplayMessage.error(header, content);
        }
        return true;
    }

    public static boolean checkTotal(String number) {
        if (!typeCheck(number)) {
            return false;
        }
        double mark = Double.parseDouble(number);
        if (mark > maxScore || mark < minScore) {
            String header = "Error: Total Out of Bounds";
            String content = "Please enter a number between " + VariableConversions.doubleToString(minScore)
                    + " and " + VariableConversions.doubleToString(maxScore) + ".";
            DisplayMessage.error(header, content);
            return false;
        }
        return true;
    }

    public static boolean checkScore(String number, double total) {
        if (!typeCheck(number)) {
            return false;
        }
        double score = Double.parseDouble(number);
        total *= 1.05;
        if (score > total) {
            String header = "Error: Score Out of Bounds";
            String content = "Please enter a number between " + VariableConversions.doubleToString(minScore)
                    + " and " + VariableConversions.doubleToString(total) + ".";
            DisplayMessage.error(header, content);
            return false;
        }
        return true;
    }
}
