package View;

import Controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class GameSettingMenu extends Application {
    public static Stage stage;
    public static Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        GameSettingMenu.stage = stage;
        URL url = StartMenu.class.getResource("/FXML/GameSettingMenu.fxml");
        Pane root = FXMLLoader.load(url);
        scene = new Scene(root);
        ApplicationController.applyColorAffects(scene);
        stage.setScene(scene);
        stage.show();
    }

}
