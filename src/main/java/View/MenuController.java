package View;

import javafx.stage.Stage;

public class MenuController {
    public static void goToMainMenu(Stage stage) {
        MainMenu mainMenu = new MainMenu();
        try {
            mainMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goToStartMenu(Stage stage) {
        StartMenu startMenu = new StartMenu();
        try {
            startMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void goToAvatarMenu(Stage stage) {
        AvatarMenu avatarMenu = new AvatarMenu();
        try {
            avatarMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goToProfileMenu(Stage stage) {
        ProfileMenu profileMenu = new ProfileMenu();
        try {
            profileMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goToGameSettingMenu(Stage stage) {
        GameSettingMenu gameSettingMenu = new GameSettingMenu();
        try {
            gameSettingMenu.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
