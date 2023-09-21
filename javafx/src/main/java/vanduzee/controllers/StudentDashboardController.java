package vanduzee.controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vanduzee.App;
import vanduzee.controllers.studentControllers.ViewStudentController;
import vanduzee.models.tableFormatting.TableUtils;
import vanduzee.models.utility.FileIO;
import vanduzee.models.utility.ManageConsole;
import vanduzee.objects.MasterList;
import vanduzee.objects.Student;

public class StudentDashboardController {
    MasterList master;

    @FXML
    public void initialize() {
        ManageConsole.clearConsole();
        master = MasterList.getInstance();
        refreshTable();
    }

    @FXML
    private TableColumn<Student, String> student_assignments;

    @FXML
    private TableColumn<Student, String> student_id;

    @FXML
    private TableColumn<Student, String> student_name;

    @FXML
    private TableView<Student> student_table;

    @FXML
    private TableColumn<Student, String> student_testGrade;

    @FXML
    private TableColumn<Student, String> student_tests;

    @FXML
    private TableColumn<Student, String> student_totalGrade;

    @FXML
    void assignment_dashboard(ActionEvent event) throws IOException {
        App.setRoot("/Dashboard_Assignment");
    }

    @FXML
    void option_load(ActionEvent event) {
        master = FileIO.loadSingletonFromFile();
        MasterList.setInstance(master);
        refreshTable();
    }

    @FXML
    void option_save(ActionEvent event) {
        FileIO.saveSingletonToFile();
    }

    @FXML
    void student_add(ActionEvent event) throws IOException {
        FXMLLoader studentAdd = new FXMLLoader(getClass().getResource("/student/addStudent.fxml"));
        Parent newRoot = studentAdd.load();
        Stage addStudentStage = new Stage();
        addStudentStage.initModality(Modality.APPLICATION_MODAL);
        addStudentStage.initStyle(StageStyle.DECORATED);
        Scene newScene = new Scene(newRoot);
        addStudentStage.setScene(newScene);
        ManageConsole.clearConsole();
        addStudentStage.showAndWait();

        refreshTable();
    }

    @FXML
    void student_dashboard(ActionEvent event) {

    }

    @FXML
    void student_view(ActionEvent event) throws IOException {
        Student student = student_table.getSelectionModel().getSelectedItem();
        try {
            if (student != null) {
                Stage viewStudentStage = new Stage();
                viewStudentStage.initModality(Modality.APPLICATION_MODAL);
                viewStudentStage.initStyle(StageStyle.DECORATED);

                FXMLLoader studentViewLoader = new FXMLLoader(
                        getClass().getResource("/student/viewStudent.fxml"));
                Parent studentViewRoot = studentViewLoader.load();
                ViewStudentController studentViewController = studentViewLoader.getController();
                studentViewController.setStudent(student);

                Scene newScene = new Scene(studentViewRoot);
                viewStudentStage.setScene(newScene);
                ManageConsole.clearConsole();
                viewStudentStage.showAndWait();

                refreshTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void test_dashboard(ActionEvent event) throws IOException {
        App.setRoot("/Dashboard_Test");
    }

    private void refreshTable() {
        master = MasterList.getInstance();
        ObservableList<Student> studentList = FXCollections.observableArrayList(master.getMasterStudents());
        student_table.setItems(studentList);

        student_name.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        student_id.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        student_assignments.setCellValueFactory(new PropertyValueFactory<>("tableAssignments"));
        student_tests.setCellValueFactory(new PropertyValueFactory<>("tableTests"));
        student_testGrade.setCellValueFactory(new PropertyValueFactory<>("tableTestGrade"));
        student_totalGrade.setCellValueFactory(new PropertyValueFactory<>("tableOverallGrade"));

        TableUtils.centerColumnValues(student_id);
        TableUtils.centerColumnValues(student_assignments);
        TableUtils.centerColumnValues(student_tests);
        TableUtils.centerColumnValues(student_testGrade);
        TableUtils.centerColumnValues(student_totalGrade);

        student_table.refresh();
    }

}
