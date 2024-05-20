package Model;

import View.MainMenu;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import java.util.Random;

public enum Avatar {
    SOUL("/Images/Avatars/avatar1.png"),
    BLUE_MONSTER("/Images/Avatars/avatar3.png"),
    PINK_DRAGON("/Images/Avatars/avatar2.png"),
    SKELETON("/Images/Avatars/avatar4.png"),
    GREEN_MONSTER("/Images/Avatars/avatar5.png"),
    STONE_MONSTER("/Images/Avatars/avatar6.png"),
    SOLDIER("/Images/Avatars/avatar7.png");
    private String url;

    Avatar(String url) {
        this.url = url;
    }

    public static Avatar getRandomAvatar() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }

    public static void addAllElements(ChoiceBox avatarChooser) {
        for (Avatar avatar : values()) {
            avatarChooser.getItems().add(avatar.name());
        }
    }

    public static Avatar getAvataraByName(String value) {
        for (Avatar avatar : values()) {
            if (avatar.name().equals(value))
                return avatar;
        }
        return null;
    }

    public String getUrl() {
        return url;
    }

    public Image getImage() {
        return new Image(MainMenu.class.getResource(User.getLoggedInUser().getAvatar().getUrl()).toString());
    }
}
