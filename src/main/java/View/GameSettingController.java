package View;

import Controller.ApplicationController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameSettingController {

    public ChoiceBox level;
    public CheckBox blackOrWhite;
    public ImageView soundMode;
    public CheckBox wasd;

    public void initialize() {
        int difficulty = ApplicationController.getGameDifficulty();
        level.getItems().addAll("Easy", "Medium", "Hard");
        level.setValue(difficulty == 1 ? "Easy" : difficulty == 2 ? "Medium" : "Hard");
        soundMode.setImage(new Image(GameSettingController.class.getResource("/Images/Icons/" + (ApplicationController.isSoundMuted() ? "mute" : "unmute") + ".png").toExternalForm()));
        blackOrWhite.setSelected(ApplicationController.isBlackAndWhite());
        wasd.setSelected(ApplicationController.isWASD());
        level.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            Controller.ApplicationController.setGameDifficulty(newValue.intValue() + 1);
        });
        blackOrWhite.setOnAction(event -> {
            Controller.ApplicationController.setBlackAndWhite(blackOrWhite.isSelected());
            Controller.ApplicationController.applyColorAffects(GameSettingMenu.scene);
        });
        wasd.setOnAction(event -> {
            Controller.ApplicationController.setWASD(wasd.isSelected());
        });
    }

    public void muteOrUnmute(MouseEvent mouseEvent) {
        Controller.GameSettingMenu.changeSoundMode(soundMode);
    }

    public void back(MouseEvent mouseEvent) {
        MenuController.goToMainMenu(GameSettingMenu.stage);
    }
}
