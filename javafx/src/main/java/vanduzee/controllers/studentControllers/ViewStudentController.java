package vanduzee.controllers.studentControllers;

/* #region imports */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vanduzee.models.objectManagement.objectUtils.Remove;
import vanduzee.models.tableFormatting.TableUtils;
import vanduzee.models.utility.ManageConsole;
import vanduzee.models.utility.StageManagement;
import vanduzee.models.utility.VariableConversions;
import vanduzee.models.validation.DisplayMessage;
/* #endregion */
import vanduzee.objects.Assignment;
import vanduzee.objects.Student;
import vanduzee.objects.Test;

public class ViewStudentController {
    Student selectedStudent;

    public void setStudent(Student student) {
        selectedStudent = student;
        updateAll();
    }

    /* #region fxml variables */
    /* #region assignment table */
    @FXML
    private TableColumn<Assignment, String> assignment_dateDue;

    @FXML
    private TableColumn<Assignment, String> assignment_dateSubmit;

    @FXML
    private TableColumn<Assignment, String> assignment_name;

    @FXML
    private TableColumn<Assignment, String> assignment_percent;

    @FXML
    private TableColumn<Assignment, String> assignment_score;

    @FXML
    private TableView<Assignment> assignment_table;

    @FXML
    private TableColumn<Assignment, String> assignment_total;
    /* #endregion */

    @FXML
    private Label lbl_grade;

    @FXML
    private Label lbl_id;

    @FXML
    private Label lbl_name;

    /* #region test table */
    @FXML
    private TableColumn<Test, String> test_dateDue;

    @FXML
    private TableColumn<Test, String> test_dateSubmit;

    @FXML
    private TableColumn<Test, String> test_name;

    @FXML
    private TableColumn<Test, String> test_percent;

    @FXML
    private TableColumn<Test, String> test_score;

    @FXML
    private TableView<Test> test_table;

    @FXML
    private TableColumn<Test, String> test_total;
    /* #endregion */
    /* #endregion */

    /* #region buttons */
    @FXML
    void editAssignment(ActionEvent event) {
        Assignment assignment = assignment_table.getSelectionModel().getSelectedItem();
        try {
            if (assignment != null) {
                Stage viewAssignmentStage = new Stage();
                viewAssignmentStage.initModality(Modality.APPLICATION_MODAL);
                viewAssignmentStage.initStyle(StageStyle.DECORATED);

                FXMLLoader assignmentViewLoader = new FXMLLoader(
                        getClass().getResource("/student/studentAssignment.fxml"));
                Parent assignmentViewRoot = assignmentViewLoader.load();
                StudentAssignmentController assignmentViewController = assignmentViewLoader.getController();
                assignmentViewController.setAssignment(assignment, selectedStudent);

                Scene newScene = new Scene(assignmentViewRoot);
                viewAssignmentStage.setScene(newScene);
                ManageConsole.clearConsole();
                viewAssignmentStage.showAndWait();

                updateAssignmentTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editTest(ActionEvent event) {
        Test test = test_table.getSelectionModel().getSelectedItem();
        try {
            if (test != null) {
                Stage viewTestStage = new Stage();
                viewTestStage.initModality(Modality.APPLICATION_MODAL);
                viewTestStage.initStyle(StageStyle.DECORATED);

                FXMLLoader testViewLoader = new FXMLLoader(
                        getClass().getResource("/student/studentTest.fxml"));
                Parent testViewRoot = testViewLoader.load();
                StudentTestController testViewController = testViewLoader.getController();
                testViewController.setTest(test, selectedStudent);

                Scene newScene = new Scene(testViewRoot);
                viewTestStage.setScene(newScene);
                ManageConsole.clearConsole();
                viewTestStage.showAndWait();

                updateTestTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void exitWindow(ActionEvent event) {
        StageManagement.closeScreen(event);
    }

    @FXML
    void removeStudent(ActionEvent event) {
        String header = "Warning: Removing Student";
        String content = "This will permanently remove this student. Are you certain you wish to proceed?";
        if (DisplayMessage.confirm(header, content)) {
            Remove.rmStudent(selectedStudent);
            StageManagement.closeScreen(event);
        }
    }
    /* #endregion */

    /* #region updaters */
    private void updateAll() {
        updateAssignmentTable();
        updateLabels();
        updateTestTable();
    }

    private void updateTestTable() {
        ObservableList<Test> testList = FXCollections.observableArrayList(selectedStudent.getTests());
        test_table.setItems(testList);

        test_name.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        test_dateSubmit.setCellValueFactory(new PropertyValueFactory<>("tableDateSubmit"));
        test_dateDue.setCellValueFactory(new PropertyValueFactory<>("tableDateDue"));
        test_score.setCellValueFactory(new PropertyValueFactory<>("tableScore"));
        test_total.setCellValueFactory(new PropertyValueFactory<>("tableTotal"));
        test_percent.setCellValueFactory(new PropertyValueFactory<>("tablePercent"));

        TableUtils.centerColumnValues(test_dateSubmit);
        TableUtils.centerColumnValues(test_dateDue);
        TableUtils.centerColumnValues(test_score);
        TableUtils.centerColumnValues(test_total);
        TableUtils.centerColumnValues(test_percent);

        test_table.refresh();
    }

    private void updateAssignmentTable() {
        ObservableList<Assignment> assignmentList = FXCollections.observableArrayList(selectedStudent.getAssignments());
        assignment_table.setItems(assignmentList);

        assignment_name.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        assignment_dateSubmit.setCellValueFactory(new PropertyValueFactory<>("tableDateSubmit"));
        assignment_dateDue.setCellValueFactory(new PropertyValueFactory<>("tableDateDue"));
        assignment_score.setCellValueFactory(new PropertyValueFactory<>("tableScore"));
        assignment_total.setCellValueFactory(new PropertyValueFactory<>("tableTotal"));
        assignment_percent.setCellValueFactory(new PropertyValueFactory<>("tablePercent"));

        TableUtils.centerColumnValues(assignment_dateSubmit);
        TableUtils.centerColumnValues(assignment_dateDue);
        TableUtils.centerColumnValues(assignment_score);
        TableUtils.centerColumnValues(assignment_total);
        TableUtils.centerColumnValues(assignment_percent);

        assignment_table.refresh();
    }

    private void updateLabels() {
        lbl_name.setText("Student Name: " + selectedStudent.getName());
        lbl_id.setText("Student ID: " + VariableConversions.intToString(selectedStudent.getId()));
        String gradeText = "";
        if (selectedStudent.getTotalGrade() == -1) {
            gradeText = "N/A";
        } else {
            gradeText = VariableConversions.doubleToString(selectedStudent.getTotalGrade());
        }
        lbl_grade.setText("Total Grade: " + gradeText);
    }
    /* #endregion */

}
