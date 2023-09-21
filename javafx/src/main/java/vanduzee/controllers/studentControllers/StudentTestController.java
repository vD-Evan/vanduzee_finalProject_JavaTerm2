package vanduzee.controllers.studentControllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vanduzee.models.objectManagement.studentSubmission.SubmitTest;
import vanduzee.models.utility.ManageConsole;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.utility.VariableConversions;
import vanduzee.models.validation.DateValidator;
import vanduzee.models.validation.DoubleValidator;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class StudentTestController {
    Student selectedStudent;
    Test selectedTest;

    public void setTest(Test test, Student student) {
        selectedTest = test;
        selectedStudent = student;
        updateLabels();
    }

    @FXML
    private DatePicker dp_date;

    @FXML
    private Label lbl_date;

    @FXML
    private Label lbl_final;

    @FXML
    private Label lbl_late;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_weight;

    @FXML
    private Label lbl_weightedScore;

    @FXML
    private TextField txt_score;

    @FXML
    void commitChange(ActionEvent event) {
        if (!DateValidator.check(dp_date.getValue())) {
            ManageConsole.printS("date validation failed");
            return;
        }
        if (!DoubleValidator.checkScore(txt_score.getText(), selectedTest.getTotal())) {
            ManageConsole.printS("score validation failed");
            return;
        }

        SubmitTest.entry(dp_date.getValue(), txt_score.getText(), selectedTest);
        updateLabels();
    }

    @FXML
    void goBack(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    private void updateLabels() {
        lbl_name.setText(selectedTest.getName());
        lbl_total.setText(VariableConversions.doubleToString(selectedTest.getTotal()));
        lbl_date.setText(VariableConversions.localDateToString(selectedTest.getDateDue()) + ", 2023");
        if (selectedTest.getDateSubmit().isAfter(LocalDate.of(2023, 9, 1))) {
            dp_date.setValue(selectedTest.getDateSubmit());
        }
        if (selectedTest.getDaysLate() == -1) {
            lbl_late.setText("-");
        } else {
            lbl_late.setText(VariableConversions.intToString(selectedTest.getDaysLate()));
        }
        lbl_weight.setText(VariableConversions.doubleToString(selectedTest.getWeight() * 100) + "%");

        ManageConsole.printS(selectedTest.getScoreWeighted());
        if (selectedTest.getScoreWeighted() == -1) {
            lbl_weightedScore.setText("-");
        } else {
            lbl_weightedScore
                    .setText(VariableConversions.doubleToString(selectedTest.getScoreWeighted() * 100) + "%");
        }

        ManageConsole.printS(selectedTest.getFinalMark());
        if (selectedTest.getFinalMark() == -1) {
            lbl_final.setText("-");
        } else {
            lbl_final.setText(VariableConversions.doubleToString(selectedTest.getFinalMark() * 100) + "%");
        }
    }

}
