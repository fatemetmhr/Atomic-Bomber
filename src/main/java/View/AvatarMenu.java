package View;

import Controller.ApplicationController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class AvatarMenu extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        AvatarMenu.stage = stage;
        URL url = StartMenu.class.getResource("/FXML/AvatarMenu.fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        ApplicationController.applyColorAffects(scene);
        stage.setScene(scene);
        stage.show();
    }

}
