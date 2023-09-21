package vanduzee.models.objectManagement.objectUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class Update {
    public static void uAssignment(Assignment assignment) {
        double total = assignment.getTotal();
        double weight = assignment.getWeight();
        LocalDate dateDue = assignment.getDateDue();

        double score = assignment.getScore();
        if (score == -1) {
            return;
        }

        double scoreWeighted = score / total * weight;
        assignment.setScoreWeighted(scoreWeighted);
        LocalDate dateSubmit = assignment.getDateSubmit();

        int daysLate = calculateDateDifference(dateDue, dateSubmit);
        assignment.setDaysLate(daysLate);

        double finalMark = scoreWeighted;
        switch (daysLate) {
            case 4:
                finalMark *= 0.0;
                break;
            case 3:
                finalMark *= 0.7;
                break;
            case 2:
                finalMark *= 0.8;
                break;
            case 1:
                finalMark *= 0.9;
                break;
            default:
                finalMark *= 1.0;
                break;
        }
        assignment.setFinalMark(finalMark);
        uStudent();
    }

    public static void uTest(Test test) {
        double total = test.getTotal();
        double weight = test.getWeight();
        LocalDate dateDue = test.getDateDue();

        double score = test.getScore();
        if (score == -1) {
            return;
        }

        double scoreWeighted = score / total * weight;
        test.setScoreWeighted(scoreWeighted);

        LocalDate dateSubmit = test.getDateSubmit();
        int daysLate = calculateDateDifference(dateDue, dateSubmit);
        test.setDaysLate(daysLate);
        double finalMark = scoreWeighted;
        switch (daysLate) {
            case 0:
                finalMark *= 1;
                break;
            default:
                finalMark *= 0;
                break;
        }
        test.setFinalMark(finalMark);
        uStudent();
    }

    private static int calculateDateDifference(LocalDate dateDue, LocalDate dateSubmit) {
        if (dateDue.isAfter(dateSubmit) || dateDue.isEqual(dateSubmit)) {
            return 0;
        }

        long daysDifference = ChronoUnit.DAYS.between(dateDue, dateSubmit);

        if ((int) daysDifference >= 1 && (int) daysDifference <= 3) {
            return (int) daysDifference;
        }
        return 4;
    }

    private static void uStudent() {
        MasterList master = MasterList.getInstance();
        if (master.getMasterStudents() == null || master.getMasterStudents().isEmpty()) {
            return;
        }

        for (Student student : master.getMasterStudents()) {

            double finalGrade = 0;
            if (student.getAssignments() != null || !student.getAssignments().isEmpty()) {
                for (Assignment assignment : student.getAssignments()) {
                    finalGrade += assignment.getFinalMark();
                }
            }

            double testWeights = 0;
            double testGrade = 0;
            if (student.getTests() != null || !student.getTests().isEmpty()) {
                for (Test test : student.getTests()) {
                    testWeights += test.getWeight();
                    testGrade += test.getFinalMark();
                    finalGrade += test.getFinalMark();
                }
            }
            double testNetGrade = testGrade / testWeights * 100;

            student.setTotalGrade(finalGrade);
            student.setTotalTest(testNetGrade);

        }

    }
}