package vanduzee.controllers.studentControllers;

/* #region imports */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vanduzee.models.objectManagement.createObject.CreateStudent;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.validation.IntegerValidator;
import vanduzee.models.validation.StringValidator;
/* #endregion */

public class AddStudentController {

    /* #region fxml variables */
    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_name;
    /* #endregion */

    /* #region buttons */
    @FXML
    void cancelAdd(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    @FXML
    void confirmAdd(ActionEvent event) {
        if (!StringValidator.checkStudentName(txt_name.getText())) {
            return;
        }
        if (!IntegerValidator.checkId(txt_id.getText())) {
            return;
        }

        String newName = txt_name.getText();
        int newId = Integer.parseInt(txt_id.getText());

        CreateStudent.newStudent(newName, newId);
        StageManagement.closeScreen(event);
    }
    /* #endregion */

}
