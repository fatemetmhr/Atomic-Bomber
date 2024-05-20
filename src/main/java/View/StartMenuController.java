package View;

import Model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import Controller.*;

public class StartMenuController {
    public TextField username;
    public PasswordField password;

    public void loginButton() {
        if(LoginAndRegisterMenuController.loginUser(username.getText(), password.getText()))
            return;
        MenuController.goToMainMenu(StartMenu.stage);
    }

    public void registerButton() {
        LoginAndRegisterMenuController.registerUser(username.getText(), password.getText());
    }

    public void startAsGuestButton() {
        User.setLoggedInUser(new User("Guest", ""));
        AlertController.showInformation("Welcome!", "You are now logged in as a guest.");
        MenuController.goToMainMenu(StartMenu.stage);
    }
}
