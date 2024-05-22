package Controller;

import Model.User;
import javafx.scene.control.ChoiceBox;
import Model.Avatar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AvatarMenuController {
    public static void addAllAvatars(ChoiceBox avatarChooser) {
        Avatar.addAllElements(avatarChooser);
        avatarChooser.setValue(User.getLoggedInUser().getAvatar().name());
    }

    public static void setNewAvatar(ImageView avatarView, String value) {
        try {
            Avatar avatar = Avatar.getAvataraByName(value);
            User.getLoggedInUser().setAvatar(avatar);
            avatarView.setImage(avatar.getImage());
        } catch (Exception e) {
            System.out.println(value);
        }
    }

    public static void setAvatarImage(Image image) {
        User.getLoggedInUser().setImage(image);
    }
}
