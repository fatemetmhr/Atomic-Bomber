package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Shot {

    public static int sizeX = 166 / 10;
    public static int sizeY = 62 / 10;

    public Bullet(int x, int y, double dir) {
        super(x, y, dir, sizeX, sizeY);
        setFill(new ImagePattern(new Image(Bullet.class.getResource("/Images/Icons/bullet.png").toString())));
    }

}
