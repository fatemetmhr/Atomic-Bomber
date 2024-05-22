package Controller;

import Model.User;
import View.AlertController;
import javafx.scene.image.ImageView;

public class ProfileMenuController {

    public static void deleteAccount() {
        User.deleteUser(User.getLoggedInUser());
        User.setLoggedInUser(null);
    }

    public static boolean changeUsernameAndPassword(String username, String password) {
        if (username.equals(User.getLoggedInUser().getUsername())) {
            if (password.equals("")) {
                AlertController.showError("Action failed!", "Password can't be empty");
                return true;
            }
            User.getLoggedInUser().setPassword(password);
            return false;
        }
        if (checkUsernameAndPassForNewUser(username, password))
            return true;
        User.getLoggedInUser().setUsername(username);
        User.getLoggedInUser().setPassword(password);
        return false;
    }

    public static boolean checkUsernameAndPassForNewUser(String username, String password) {
        if (username.equals("") || password.equals("")) {
            AlertController.showError("Action failed!", "Username or password can not be empty");
            return true;
        }
        User user = User.getUserByUsername(username);
        if (user != null) {
            AlertController.showError("Action failed!", "Username already exists");
            return true;
        }
        return false;
    }

    public static void logoutUser() {
        if (User.getLoggedInUser().getUsername().equals("Guest"))
            User.deleteUser(User.getLoggedInUser());
        User.setLoggedInUser(null);
    }

    public static void setAvatar(ImageView avatar) {
        avatar.setImage(User.getLoggedInUser().getAvatar().getImage());
    }
}
