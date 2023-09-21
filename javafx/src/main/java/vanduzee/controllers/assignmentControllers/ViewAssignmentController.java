package vanduzee.controllers.assignmentControllers;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vanduzee.models.objectManagement.modifyObject.ModifyAssignment;
import vanduzee.models.objectManagement.objectUtils.Remove;
import vanduzee.models.utility.ManageConsole;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.utility.VariableConversions;
import vanduzee.models.validation.DateValidator;
import vanduzee.models.validation.DisplayMessage;
import vanduzee.models.validation.DoubleValidator;
import vanduzee.models.validation.StringValidator;
import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;

public class ViewAssignmentController {
    Assignment selectedAssignment;

    public void setAssignment(Assignment assignment) {
        selectedAssignment = assignment;
        updateAll();
    }

    @FXML
    private DatePicker dp_date;

    @FXML
    private Label lbl_submission;

    @FXML
    private TextField txt_marks;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_weight;

    @FXML
    void confirmChanges(ActionEvent event) {
        String newName;
        String displayName;
        double newTotal;
        String displayTotal;
        LocalDate newDate;
        String displayDate;
        double newWeight;
        String displayWeight;

        if (!StringValidator.checkAssignmentName(txt_name.getText(), selectedAssignment)) {
            ManageConsole.printS("name validation failed");
            return;
        } else {
            newName = txt_name.getText();
            displayName = ModifyAssignment.stringName(txt_name.getText());
        }

        if (!DoubleValidator.checkTotal(txt_marks.getText())) {
            ManageConsole.printS("marks validation failed");
            return;
        } else {
            newTotal = ModifyAssignment.doubleTotal(txt_marks.getText());
            displayTotal = ModifyAssignment.stringTotal(txt_marks.getText());
        }

        if (!DateValidator.check(dp_date.getValue())) {
            ManageConsole.printS("date validation failed");
            return;
        } else {
            newDate = ModifyAssignment.localDateDue(dp_date.getValue());
            displayDate = ModifyAssignment.stringDate(dp_date.getValue());
        }

        if (!DoubleValidator.checkWeight(txt_weight.getText())) {
            ManageConsole.printS("weight validation failed");
            return;
        } else {
            newWeight = ModifyAssignment.doubleWeight(txt_weight.getText());
            displayWeight = ModifyAssignment.stringWeight(txt_weight.getText());
        }

        String header = "Warning: Updating Assignment";
        String content = "This will update " + selectedAssignment.getName() + " to reflect the following:\n";
        content += displayName + displayTotal + displayDate + displayWeight;

        if (DisplayMessage.confirm(header, content)) {
            ModifyAssignment.updateMasterAssignment(selectedAssignment, newName, newTotal, newDate, newWeight);
            updateAll();
        }

    }

    @FXML
    void goBack(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    @FXML
    void removeAssignment(ActionEvent event) {

        String header = "Warning: Removing Assignment";
        String content = "This will permanently remove " + selectedAssignment.getName()
                + " from all students. Are you certain you wish to proceed?";
        if (DisplayMessage.confirm(header, content)) {
            Remove.rmAssignment(selectedAssignment);
            StageManagement.closeScreen(event);
        }
    }

    private void updateAll() {
        txt_name.setText(selectedAssignment.getName());
        txt_marks.setText(VariableConversions.doubleToString(selectedAssignment.getTotal()));
        dp_date.setValue(selectedAssignment.getDateDue());
        txt_weight.setText(VariableConversions.doubleToString(selectedAssignment.getWeight() * 100));

        MasterList master = MasterList.getInstance();
        if (master.getMasterStudents() == null || master.getMasterStudents().isEmpty()) {
            lbl_submission.setText("N / A");
        } else {
            String studentCount = VariableConversions.intToString(master.getMasterStudents().size());
            String submitCount = cycleStudents(master);
            lbl_submission.setText(submitCount + " / " + studentCount);
        }
    }

    private String cycleStudents(MasterList master) {
        int submitCounter = 0;
        ArrayList<Student> students = master.getMasterStudents();
        for (Student student : students) {
            ArrayList<Assignment> assignments = student.getAssignments();
            for (Assignment assignment : assignments) {
                submitCounter += checkAssignment(assignment);
            }
        }
        String returnSubmit = VariableConversions.intToString(submitCounter);
        return returnSubmit;
    }

    private int checkAssignment(Assignment assignment) {
        if (!assignment.getName().equals(selectedAssignment.getName())) {
            return 0;
        }
        if (assignment.getScore() == -1) {
            return 0;
        }
        return 1;
    }

}
