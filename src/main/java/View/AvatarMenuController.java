package View;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;

import static Controller.AvatarMenuController.setAvatarImage;

public class AvatarMenuController {

    public ChoiceBox<String> avatarChooser;
    public ImageView avatar;
    @FXML
    private Pane dragPane;

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
            setAvatarImage(image, avatar);
        }
    }

    public void dragOver(DragEvent dragEvent) {
        if (dragEvent.getGestureSource() != dragPane && dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(javafx.scene.input.TransferMode.COPY_OR_MOVE);
        }
        dragEvent.consume();
    }

    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an avatar");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File file = fileChooser.showOpenDialog(AvatarMenu.stage);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            setAvatarImage(image, avatar);
        }
    }

    public Image setupDragAndDrop(DragEvent dragEvent) {
        Dragboard dragboard = dragEvent.getDragboard();
        File file;
        if (dragboard.hasFiles()) {
            file = dragboard.getFiles().get(0);
            return new Image(file.toURI().toString());
        }
        return null;
    }

    public void back(MouseEvent mouseEvent) {
        MenuController.goToProfileMenu(AvatarMenu.stage);
    }
}
