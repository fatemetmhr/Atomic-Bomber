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
        URL url = MainMenu.class.getResource("/FXML/" + (ApplicationController.isWin() ? "Win" : "Loose") + ".fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        ApplicationController.applyColorAffects(scene);
        stage.setScene(scene);
        stage.show();
        Controller.ApplicationController.startMusic(StartMenu.class.getResource(
                "/Sounds/Background/" + (ApplicationController.isWin() ? "win.mp4" : "game_over.mp3")).toString());

    }
}
