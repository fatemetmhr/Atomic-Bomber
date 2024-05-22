package View;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import Controller.*;

public class StartMenuController {
    public TextField username;
    public PasswordField password;

    public void loginButton() {
        if (LoginAndRegisterMenuController.loginUser(username.getText(), password.getText()))
            return;
        MenuController.goToMainMenu(StartMenu.stage);
    }

    public void registerButton() {
        LoginAndRegisterMenuController.registerUser(username.getText(), password.getText());
    }

    public void startAsGuestButton() {
        LoginAndRegisterMenuController.setGuestUser();
        AlertController.showInformation("Welcome!", "You are now logged in as a guest.");
        MenuController.goToMainMenu(StartMenu.stage);
    }
}
