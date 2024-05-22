package Model;

import javafx.scene.image.Image;

public class BulletFire extends BurningAnimation {

    private static int sizeX = 200 / 2;
    private static int sizeY = 282 / 2;

    public BulletFire(int x, int y, Game game) {
        super(x, y, game);
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
        Image image = new Image(Plane.class.getResource("/Images/Icons/bullet_fire.gif").toString());
        imageView.setImage(image);
        game.getAllBurningAnimations().add(this);
    }
}
