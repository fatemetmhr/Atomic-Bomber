package View;

import Model.User;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProfileMenuController {

    public TextField username;
    public TextField password;
    public ImageView avatar;

    public void initialize(){
        username.setText(User.getLoggedInUser().getUsername());
        password.setText(User.getLoggedInUser().getPassword());
        Controller.ProfileMenuController.setAvatar(avatar);
    }


    public void deleteAccount() {
        if(!AlertController.showConfirmation("Heads up!", "Are you sure you want to delete your account?"))
            return;
        Controller.ProfileMenuController.deleteAccount();
        MenuController.goToStartMenu(ProfileMenu.stage);
    }

    public void backToMainMenu(MouseEvent mouseEvent) {
        MenuController.goToMainMenu(ProfileMenu.stage);
    }

    public void saveNewUsernameAndPass(MouseEvent mouseEvent) {
        if(Controller.ProfileMenuController.changeUsernameAndPassword(username.getText(), password.getText()))
            return;
        AlertController.showInformation("Success!", "Your changes have been saved successfully.");
    }

    public void logoutFromAccount(MouseEvent mouseEvent) {
        Controller.ProfileMenuController.logoutUser();
        MenuController.goToStartMenu(ProfileMenu.stage);
    }

    public void enterAvatarMenu(MouseEvent mouseEvent) {
        MenuController.goToAvatarMenu(ProfileMenu.stage);
    }
}
