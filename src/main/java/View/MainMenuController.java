package View;

import Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainMenuController {

    public ImageView avatar;
    public Label username;

    public void initialize(){
        username.setText(User.getLoggedInUser().getUsername());
        Controller.ProfileMenuController.setAvatar(avatar);
    }


    public void startNewGame(MouseEvent mouseEvent) {
    }

    public void continueSavedGame(MouseEvent mouseEvent) {
    }

    public void profileSettings(MouseEvent mouseEvent) {
        ProfileMenu profileMenu = new ProfileMenu();
        try{
            profileMenu.start(MainMenu.stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gameSettings(MouseEvent mouseEvent) {
        MenuController.goToGameSettingMenu(MainMenu.stage);
    }

    public void scoreBoard(MouseEvent mouseEvent) {
    }

    public void exitFromGame(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setAvatar(Image image) {
        this.avatar.setImage(image);
    }
}
