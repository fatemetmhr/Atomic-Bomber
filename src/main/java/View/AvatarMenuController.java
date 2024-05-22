package View;

import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class AvatarMenuController {

    public ChoiceBox<String> avatarChooser;
    public ImageView avatar;
    public ImageView dragImage;


    public void initialize() {
        Controller.ProfileMenuController.setAvatar(avatar);
        Controller.AvatarMenuController.addAllAvatars(avatarChooser);
        avatarChooser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Controller.AvatarMenuController.setNewAvatar(avatar, newValue + "");
        });

    }

    public void dragDropped(DragEvent dragEvent) {
        Image image = setupDragAndDrop(dragEvent);
        if (image != null) {
            Controller.AvatarMenuController.setAvatarImage(image);
        }
    }

    public void dragOver(DragEvent dragEvent) {
        if (dragEvent.getGestureSource() != dragImage && dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(javafx.scene.input.TransferMode.COPY_OR_MOVE);
        }
        dragEvent.consume();
    }

    public Image setupDragAndDrop(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        File file;
        if (dragboard.hasFiles()) {
            file = dragboard.getFiles().get(0);
            Image image = new Image(file.toURI().toString());
            return image;
        }
        return null;
    }

    public void back(MouseEvent mouseEvent) {
        MenuController.goToProfileMenu(AvatarMenu.stage);
    }
}
