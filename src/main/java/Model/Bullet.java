package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bullet extends Shot {

    public static int sizeX = 370 / 13;
    public static int sizeY = 138 / 13;

    public Bullet(int x, int y, double dir) {
        super(x, y, dir, sizeX, sizeY);
        setFill(new ImagePattern(new Image(Bullet.class.getResource("/Images/Icons/bullet.png").toString())));
    }

    @Override
    protected boolean willDestroy(Obstacle obstacle) {
        return getBoundsInParent().intersects(obstacle.getBoundsInParent());
    }

    @Override
    protected void remove() {
        super.remove();
        BulletFire bulletFire = new BulletFire((int) getX(), (int) getY() - 100, Game.getCurrentGame());
    }

}
