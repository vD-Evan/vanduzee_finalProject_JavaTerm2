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
import vanduzee.controllers.assignmentControllers.ViewAssignmentController;
import vanduzee.models.tableFormatting.TableUtils;
import vanduzee.models.utility.FileIO;
import vanduzee.models.utility.ManageConsole;
import vanduzee.objects.Assignment;
import vanduzee.objects.MasterList;

public class AssignmentDashboardController {
    MasterList master;

    @FXML
    public void initialize() {
        ManageConsole.clearConsole();
        master = MasterList.getInstance();
        refreshTable();
    }

    @FXML
    private TableColumn<Assignment, String> assignment_date;

    @FXML
    private TableColumn<Assignment, String> assignment_name;

    @FXML
    private TableView<Assignment> assignment_table;

    @FXML
    private TableColumn<Assignment, String> assignment_total;

    @FXML
    private TableColumn<Assignment, String> assignment_weight;

    @FXML
    void assignment_add(ActionEvent event) throws IOException {
        FXMLLoader assignmentAdd = new FXMLLoader(getClass().getResource("/assignment/addAssignment.fxml"));
        Parent newRoot = assignmentAdd.load();
        Stage addAssignmentStage = new Stage();
        addAssignmentStage.initModality(Modality.APPLICATION_MODAL);
        addAssignmentStage.initStyle(StageStyle.DECORATED);
        Scene newScene = new Scene(newRoot);
        addAssignmentStage.setScene(newScene);
        ManageConsole.clearConsole();
        addAssignmentStage.showAndWait();
        refreshTable();
    }

    @FXML
    void assignment_dashboard(ActionEvent event) {

    }

    @FXML
    void assignment_view(ActionEvent event) throws IOException {
        Assignment assignment = assignment_table.getSelectionModel().getSelectedItem();
        try {
            if (assignment != null) {
                Stage viewAssignmentStage = new Stage();
                viewAssignmentStage.initModality(Modality.APPLICATION_MODAL);
                viewAssignmentStage.initStyle(StageStyle.DECORATED);

                FXMLLoader assignmentViewLoader = new FXMLLoader(
                        getClass().getResource("/assignment/viewAssignment.fxml"));
                Parent assignmentViewRoot = assignmentViewLoader.load();
                ViewAssignmentController assignmentViewController = assignmentViewLoader.getController();
                assignmentViewController.setAssignment(assignment);

                Scene newScene = new Scene(assignmentViewRoot);
                viewAssignmentStage.setScene(newScene);
                ManageConsole.clearConsole();
                viewAssignmentStage.showAndWait();

                refreshTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    void student_dashboard(ActionEvent event) throws IOException {
        App.setRoot("/Dashboard_Student");
    }

    @FXML
    void test_dashboard(ActionEvent event) throws IOException {
        App.setRoot("/Dashboard_Test");
    }

    private void refreshTable() {
        master = MasterList.getInstance();
        ObservableList<Assignment> assignmentList = FXCollections.observableArrayList(master.getMasterAssignments());
        assignment_table.setItems(assignmentList);

        assignment_name.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        assignment_total.setCellValueFactory(new PropertyValueFactory<>("tableTotal"));
        assignment_weight.setCellValueFactory(new PropertyValueFactory<>("tableWeight"));
        assignment_date.setCellValueFactory(new PropertyValueFactory<>("tableDateDue"));

        TableUtils.centerColumnValues(assignment_total);
        TableUtils.centerColumnValues(assignment_weight);
        TableUtils.centerColumnValues(assignment_date);

        assignment_table.refresh();
    }

}
