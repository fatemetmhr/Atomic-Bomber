package Model;

import Controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class ObstacleBullet extends Shot{

    public static int sizeX = 166 / 8;
    public static int sizeY = 62 / 8;

    private Rectangle owner;

    public ObstacleBullet(int x, int y, double dir, Rectangle owner) {
        super(x, y, dir, sizeX, sizeY);
        this.owner = owner;
        speedY *= (2 + ApplicationController.getGameDifficulty() / 10.0);
        setFill(new ImagePattern(new Image(Bullet.class.getResource("/Images/Icons/tank_bullet.png").toString())));
    }

    @Override
    protected void checkForCollision() {
        Plane plane = Game.getCurrentGame().getPlane();
        if(getBoundsInParent().intersects(plane.getBoundsInParent())){
            plane.gotDamaged();
            this.remove();
        }
    }


    public Rectangle getOwner() {
        return owner;
    }
}
