package Controller;

import Model.*;
import View.*;

public class LoginAndRegisterMenuController {

    public static boolean loginUser(String username, String password) {
        User user = User.getUserByUsername(username);
        if (user == null) {
            AlertController.showError("Failed to login", "Username does not exist");
            return true;
        }
        if (!user.getPassword().equals(password)) {
            AlertController.showError("Failed to login", "Password is incorrect");
            return true;
        }
        AlertController.showInformation("Login successfully", "Welcome " + user.getUsername() + "!");
        User.setLoggedInUser(user);
        Controller.ApplicationController.resetGameSetting();
        return false;
    }

    public static boolean registerUser(String username, String password) {
        if (ProfileMenuController.checkUsernameAndPassForNewUser(username, password))
            return true;
        new User(username, password);
        AlertController.showInformation("Register successfully", "You can now login to the game!");
        return false;
    }

    public static void setGuestUser() {
        User.setLoggedInUser(new User("Guest", ""));
    }
}
