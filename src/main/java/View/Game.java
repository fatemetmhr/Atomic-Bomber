package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class Game extends Application {


    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Game.stage = stage;
        URL url = Game.class.getResource("/FXML/Game.fxml");
        Pane root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
