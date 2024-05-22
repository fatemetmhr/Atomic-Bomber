package View;

import Controller.ApplicationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class GameOver extends Application {

    public static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        GameOver.stage = stage;
        URL url = MainMenu.class.getResource("/FXML/GameOver.fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        ApplicationController.applyColorAffects(scene);
        stage.setScene(scene);
        stage.show();
    }
}
