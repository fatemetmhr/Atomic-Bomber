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

    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        Pane root = new BorderPane();
        root.getStyleClass().add("MainPane");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/CSS/style.css").toExternalForm());
        Controller.GameController.setGameSettings(root);
        ApplicationController.applyColorAffects(scene);
        scene.setOnKeyPressed(event -> Controller.GameController.keyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> Controller.GameController.keyReleased(event.getCode()));
        stage.setScene(scene);
        stage.show();
    }


}
