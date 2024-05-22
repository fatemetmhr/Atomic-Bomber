package Model;

import Controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class TankBullet extends Shot{

    public static int sizeX = 166 / 10;
    public static int sizeY = 62 / 10;

    private Tank tank;

    public TankBullet(int x, int y, double dir, Tank tank) {
        super(x, y, dir, sizeX, sizeY);
        this.tank = tank;
        speedY *= ApplicationController.getGameDifficulty() * 2;
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

    public Tank getTank() {
        return tank;
    }
}
