package vanduzee.models.validation;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class DisplayMessage {
    public static boolean confirm(String header, String content) {
        boolean userChoice = false;
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmation");
        confirmAlert.setHeaderText(header);
        confirmAlert.setContentText(content);

        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            userChoice = true;
        }

        return userChoice;
    }

    public static void warning(String header, String content) {
        Alert warningAlert = new Alert(AlertType.WARNING);
        warningAlert.setTitle("Error");
        warningAlert.setHeaderText(header);
        warningAlert.setContentText(content);
        warningAlert.showAndWait();
    }

    public static void error(String header, String content) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}
