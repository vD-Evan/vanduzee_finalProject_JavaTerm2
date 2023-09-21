package vanduzee.controllers.studentControllers;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vanduzee.models.objectManagement.studentSubmission.SubmitAssignment;
import vanduzee.models.utility.ManageConsole;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.utility.VariableConversions;
import vanduzee.models.validation.DateValidator;
import vanduzee.models.validation.DoubleValidator;
import vanduzee.objects.Assignment;
import vanduzee.objects.Student;

public class StudentAssignmentController {
    Student selectedStudent;
    Assignment selectedAssignment;

    public void setAssignment(Assignment assignment, Student student) {
        selectedAssignment = assignment;
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
        if (!DoubleValidator.checkScore(txt_score.getText(), selectedAssignment.getTotal())) {
            ManageConsole.printS("score validation failed");
            return;
        }

        SubmitAssignment.entry(dp_date.getValue(), txt_score.getText(), selectedAssignment);
        updateLabels();
    }

    @FXML
    void goBack(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    private void updateLabels() {
        lbl_name.setText(selectedAssignment.getName());
        lbl_total.setText(VariableConversions.doubleToString(selectedAssignment.getTotal()));
        lbl_date.setText(VariableConversions.localDateToString(selectedAssignment.getDateDue()) + ", 2023");
        if (selectedAssignment.getDateSubmit().isAfter(LocalDate.of(2023, 9, 1))) {
            dp_date.setValue(selectedAssignment.getDateSubmit());
        }
        if (selectedAssignment.getDaysLate() == -1) {
            lbl_late.setText("-");
        } else {
            lbl_late.setText(VariableConversions.intToString(selectedAssignment.getDaysLate()));
        }
        lbl_weight.setText(VariableConversions.doubleToString(selectedAssignment.getWeight() * 100) + "%");

        ManageConsole.printS(selectedAssignment.getScoreWeighted());
        if (selectedAssignment.getScoreWeighted() == -1) {
            lbl_weightedScore.setText("-");
        } else {
            lbl_weightedScore
                    .setText(VariableConversions.doubleToString(selectedAssignment.getScoreWeighted() * 100) + "%");
        }

        ManageConsole.printS(selectedAssignment.getFinalMark());
        if (selectedAssignment.getFinalMark() == -1) {
            lbl_final.setText("-");
        } else {
            lbl_final.setText(VariableConversions.doubleToString(selectedAssignment.getFinalMark() * 100) + "%");
        }
    }

}
