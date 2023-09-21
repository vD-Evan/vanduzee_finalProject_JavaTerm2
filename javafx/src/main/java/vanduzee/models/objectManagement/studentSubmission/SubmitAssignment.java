package vanduzee.models.objectManagement.studentSubmission;

import java.time.LocalDate;

import vanduzee.models.objectManagement.objectUtils.Update;
import vanduzee.objects.Assignment;

public class SubmitAssignment {
    public static void entry(LocalDate newDate, String newScore, Assignment assignment) {
        LocalDate submit = newDate;
        double score = Double.parseDouble(newScore);

        assignment.setDateSubmit(submit);
        assignment.setScore(score);
        Update.uAssignment(assignment);
    }
}
