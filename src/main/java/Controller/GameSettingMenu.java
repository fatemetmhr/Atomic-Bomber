package Controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Model.*;

public class GameSettingMenu {
    public static void changeSoundMode(ImageView soundMode) {
        if(ApplicationController.isSoundMuted()){
            soundMode.setImage(new Image(GameSettingMenu.class.getResource("/Images/Icons/unmute.png").toExternalForm()));
            ApplicationController.unmuteMusic();
        } else {
            try {
                soundMode.setImage(new Image(GameSettingMenu.class.getResource("/Images/Icons/mute.png").toExternalForm()));
                ApplicationController.muteMusic();
            } catch (Exception e) {
                System.err.println(GameSettingMenu.class.getResource("/Images"));
                System.err.println(GameSettingMenu.class.getResource("/Images/Icons"));
                System.err.println(GameSettingMenu.class.getResource("/Images/Icons/unmute.png"));
                System.err.println(e.getMessage());
            }
        }
    }
}
