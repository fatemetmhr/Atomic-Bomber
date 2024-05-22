package Model;

import javafx.scene.image.Image;

public class BurningFire extends BurningAnimation {

    private static int sizeX = 489 / 6;
    private static int sizeY = 489 / 6;

    public BurningFire(int x, int y, Game game) {
        super(x, y, game);
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
        Image image = new Image(Plane.class.getResource("/Images/Icons/burning.gif").toString());
        imageView.setImage(image);
    }

}
