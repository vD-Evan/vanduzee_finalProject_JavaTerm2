package vanduzee.models.utility;

import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.stage.Stage;

public class StageManagement {
    public static void closeScreen(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}