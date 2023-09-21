package vanduzee.controllers.assignmentControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import vanduzee.models.objectManagement.createObject.CreateAssignment;
import vanduzee.models.utility.ManageConsole;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.validation.DateValidator;
import vanduzee.models.validation.DoubleValidator;
import vanduzee.models.validation.StringValidator;

public class AddAssignmentController {

    @FXML
    private DatePicker dp_date;

    @FXML
    private TextField txt_marks;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_weight;

    @FXML
    void cancelAdd(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    @FXML
    void confirmAdd(ActionEvent event) {

        if (!StringValidator.checkAssignmentName(txt_name.getText())) {
            ManageConsole.printS("name validation failed");
            return;
        }

        if (!DoubleValidator.checkTotal(txt_marks.getText())) {
            ManageConsole.printS("marks validation failed");
            return;
        }

        if (!DateValidator.check(dp_date.getValue())) {
            ManageConsole.printS("date validation failed");
            return;
        }

        if (!DoubleValidator.checkWeight(txt_weight.getText())) {
            ManageConsole.printS("weight validation failed");
            return;
        }

        CreateAssignment.newAssignment(txt_name.getText(), txt_marks.getText(), dp_date.getValue(),
                txt_weight.getText());
        StageManagement.closeScreen(event);
    }

}