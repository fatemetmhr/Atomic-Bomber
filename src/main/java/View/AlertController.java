package View;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;

public class AlertController {
    public static boolean showError(String header, String message) {
        return showAlert(Alert.AlertType.ERROR, header, message);
    }

    public static boolean showInformation(String header, String message) {
        return showAlert(Alert.AlertType.INFORMATION, header, message);
    }

    public static boolean showConfirmation(String header, String message) {
        return showAlert(Alert.AlertType.CONFIRMATION, header, message);
    }

    private static boolean showAlert(Alert.AlertType alertType, String header, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(alertType.toString());
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
        return alert.getResult().getButtonData() != ButtonBar.ButtonData.CANCEL_CLOSE;
    }
}
