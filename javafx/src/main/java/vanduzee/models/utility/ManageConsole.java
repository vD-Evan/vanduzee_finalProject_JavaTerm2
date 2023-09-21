package vanduzee.models.utility;

import java.time.LocalDate;

public class ManageConsole {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printS(String s) {
        System.out.println(s);
    }

    public static void printS(int s) {
        System.out.println(s);
    }

    public static void printS(boolean s) {
        System.out.println(s);
    }

    public static void printS(double s) {
        System.out.println(s);
    }

    public static void printS(LocalDate s) {
        System.out.println(s);
    }
}
