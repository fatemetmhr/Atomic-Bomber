package View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameOverController {
    public Label wave;
    public Label kills;
    public Label accuracy;

    public void initialize() {
        wave.setText("Your plane destroyed in wave " + Model.Game.getCurrentGame().getWave());
        kills.setText("Number of kills: " + Model.Game.getCurrentGame().getKills());
        accuracy.setText("Accuracy: " + Model.Game.getCurrentGame().getAccuracy());
    }


    public void back(MouseEvent mouseEvent) {
        Model.Game.setCurrentGame(null);
        MenuController.goToMainMenu(GameOver.stage);
    }
}
