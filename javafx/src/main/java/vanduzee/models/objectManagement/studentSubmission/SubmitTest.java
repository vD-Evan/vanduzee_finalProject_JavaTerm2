package vanduzee.models.objectManagement.studentSubmission;

import java.time.LocalDate;

import vanduzee.models.objectManagement.objectUtils.Update;
import vanduzee.objects.Test;

public class SubmitTest {
    public static void entry(LocalDate newDate, String newScore, Test test) {
        LocalDate submit = newDate;
        double score = Double.parseDouble(newScore);

        test.setDateSubmit(submit);
        test.setScore(score);
        Update.uTest(test);
    }
}
