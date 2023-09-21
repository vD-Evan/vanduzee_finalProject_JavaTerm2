package vanduzee.controllers.testControllers;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import vanduzee.models.objectManagement.modifyObject.ModifyTest;
import vanduzee.models.objectManagement.objectUtils.Remove;
import vanduzee.models.utility.ManageConsole;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.utility.VariableConversions;
import vanduzee.models.validation.DateValidator;
import vanduzee.models.validation.DisplayMessage;
import vanduzee.models.validation.DoubleValidator;
import vanduzee.models.validation.StringValidator;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class ViewTestController {
    Test selectedTest;

    public void setTest(Test test) {
        selectedTest = test;
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

        if (!StringValidator.checkTestName(txt_name.getText(), selectedTest)) {
            ManageConsole.printS("name validation failed");
            return;
        } else {
            newName = txt_name.getText();
            displayName = ModifyTest.stringName(txt_name.getText());
        }

        if (!DoubleValidator.checkTotal(txt_marks.getText())) {
            ManageConsole.printS("marks validation failed");
            return;
        } else {
            newTotal = ModifyTest.doubleTotal(txt_marks.getText());
            displayTotal = ModifyTest.stringTotal(txt_marks.getText());
        }

        if (!DateValidator.check(dp_date.getValue())) {
            ManageConsole.printS("date validation failed");
            return;
        } else {
            newDate = ModifyTest.localDateDue(dp_date.getValue());
            displayDate = ModifyTest.stringDate(dp_date.getValue());
        }

        if (!DoubleValidator.checkWeight(txt_weight.getText())) {
            ManageConsole.printS("weight validation failed");
            return;
        } else {
            newWeight = ModifyTest.doubleWeight(txt_weight.getText());
            displayWeight = ModifyTest.stringWeight(txt_weight.getText());
        }

        String header = "Warning: Updating Test";
        String content = "This will update " + selectedTest.getName() + " to reflect the following:\n";
        content += displayName + displayTotal + displayDate + displayWeight;

        if (DisplayMessage.confirm(header, content)) {
            ModifyTest.updateMasterTest(selectedTest, newName, newTotal, newDate, newWeight);
            updateAll();
        }

    }

    @FXML
    void goBack(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    @FXML
    void removeTest(ActionEvent event) {

        String header = "Warning: Removing Test";
        String content = "This will permanently remove " + selectedTest.getName()
                + " from all students. Are you certain you wish to proceed?";
        if (DisplayMessage.confirm(header, content)) {
            Remove.rmTest(selectedTest);
            StageManagement.closeScreen(event);
        }
    }

    private void updateAll() {
        txt_name.setText(selectedTest.getName());
        txt_marks.setText(VariableConversions.doubleToString(selectedTest.getTotal()));
        dp_date.setValue(selectedTest.getDateDue());
        txt_weight.setText(VariableConversions.doubleToString(selectedTest.getWeight() * 100));

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
            ArrayList<Test> tests = student.getTests();
            for (Test test : tests) {
                submitCounter += checkTest(test);
            }
        }
        String returnSubmit = VariableConversions.intToString(submitCounter);
        return returnSubmit;
    }

    private int checkTest(Test test) {
        if (!test.getName().equals(selectedTest.getName())) {
            return 0;
        }
        if (test.getScore() == -1) {
            return 0;
        }
        return 1;
    }

}
