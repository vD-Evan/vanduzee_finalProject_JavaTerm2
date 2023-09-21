package vanduzee.controllers;

/* #region imports */
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
import vanduzee.controllers.testControllers.ViewTestController;
import vanduzee.models.tableFormatting.TableUtils;
import vanduzee.models.utility.FileIO;
import vanduzee.models.utility.ManageConsole;
import vanduzee.objects.MasterList;
import vanduzee.objects.Test;
/* #endregion */

public class TestDashboardController {
    MasterList master;

    @FXML
    public void initialize() {
        ManageConsole.clearConsole();
        master = MasterList.getInstance();
        refreshTable();
    }

    /* #region fxml variables */
    /* #region test table */
    @FXML
    private TableColumn<Test, String> test_date;

    @FXML
    private TableColumn<Test, String> test_name;

    @FXML
    private TableView<Test> test_table;

    @FXML
    private TableColumn<Test, String> test_total;

    @FXML
    private TableColumn<Test, String> test_weight;
    /* #endregion */
    /* #endregion */

    /* #region buttons */
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
    void student_dashboard(ActionEvent event) throws IOException {
        App.setRoot("/Dashboard_Student");
    }

    @FXML
    void test_add(ActionEvent event) throws IOException {
        FXMLLoader testAdd = new FXMLLoader(getClass().getResource("/test/addTest.fxml"));
        Parent newRoot = testAdd.load();
        Stage addTestStage = new Stage();
        addTestStage.initModality(Modality.APPLICATION_MODAL);
        addTestStage.initStyle(StageStyle.DECORATED);
        Scene newScene = new Scene(newRoot);
        addTestStage.setScene(newScene);
        ManageConsole.clearConsole();
        addTestStage.showAndWait();
        refreshTable();
    }

    @FXML
    void test_dashboard(ActionEvent event) {

    }

    @FXML
    void test_view(ActionEvent event) throws IOException {
        Test test = test_table.getSelectionModel().getSelectedItem();
        try {
            if (test != null) {
                Stage viewTestStage = new Stage();
                viewTestStage.initModality(Modality.APPLICATION_MODAL);
                viewTestStage.initStyle(StageStyle.DECORATED);

                FXMLLoader testViewLoader = new FXMLLoader(
                        getClass().getResource("/test/viewTest.fxml"));
                Parent testViewRoot = testViewLoader.load();
                ViewTestController testViewController = testViewLoader.getController();
                testViewController.setTest(test);

                Scene newScene = new Scene(testViewRoot);
                viewTestStage.setScene(newScene);
                ManageConsole.clearConsole();
                viewTestStage.showAndWait();

                refreshTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /* #endregion */

    /* #region refresher */
    private void refreshTable() {
        master = MasterList.getInstance();
        ObservableList<Test> testList = FXCollections.observableArrayList(master.getMasterTests());
        test_table.setItems(testList);

        test_name.setCellValueFactory(new PropertyValueFactory<>("tableName"));
        test_total.setCellValueFactory(new PropertyValueFactory<>("tableTotal"));
        test_weight.setCellValueFactory(new PropertyValueFactory<>("tableWeight"));
        test_date.setCellValueFactory(new PropertyValueFactory<>("tableDateDue"));

        TableUtils.centerColumnValues(test_total);
        TableUtils.centerColumnValues(test_weight);
        TableUtils.centerColumnValues(test_date);

        test_table.refresh();
    }
    /* #endregion */

}
