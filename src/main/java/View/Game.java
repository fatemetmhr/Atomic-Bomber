package View;

import Controller.ApplicationController;
import Model.Plane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;

public class Game extends Application {


    private static Stage stage;
    public static GameController gameController;

    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        URL url = MainMenu.class.getResource("/FXML/Game.fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        Controller.GameController.setGameSettings(root);
        ApplicationController.applyColorAffects(scene);
        scene.setOnKeyPressed(event -> Controller.GameController.keyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> Controller.GameController.keyReleased(event.getCode()));
        stage.setScene(scene);
        stage.show();
    }


}
