package Model;

import Controller.ApplicationController;
import Controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Tank extends Obstacle {

    static double speedScale = 0.2;
    private double shootingBoard = 250;

    public Tank(Game game) {
        super((int) (1170 / 6), (int) (476 / 6), 0, 700,
                (new Random().nextInt(2) == 0 ? 1 : -1) * speedScale * ApplicationController.getGameDifficulty(), game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/tank.png").toString())));
        if (speed < 0)
            setScaleX(-1);
        score = 4;
        shootingBoard *= ApplicationController.getGameDifficulty();
    }

    public void checkForShooting() {
        if (GameController.isGameFreezed())
            return;
        Plane plane = game.getPlane();
        double distance = Math.sqrt(Math.pow(plane.getX() - getX(), 2) + Math.pow(plane.getY() - getY(), 2));
        if (distance < shootingBoard && game.isAnyTankBulletInFrame(this) == false && game.getWave() > 1) {
            shoot();
        }
    }

    private void shoot() {
        new ObstacleBullet((int) getX() + (speed < 0 ? 20 : 180), (int) getY() + 20, getShootingDirection(), this);
    }

    private double getShootingDirection() {
        if (speed < 0) {
            return 5 * Math.PI / 4;
        }
        return 2 * Math.PI - Math.PI / 4;
    }

    @Override
    public void passTime() {
        move();
        if (getX() < -sizeX)
            removeObject();
        if (getX() > 1291)
            removeObject();
        checkForShooting();
    }

    @Override
    protected void addBurnings() {
        BurningFire burningFire = new BurningFire((int) getX() + 30, (int) getY(), game);
        burnings.add(burningFire);
        burningFire = new BurningFire((int) getX() + 100, (int) getY() - 30, game);
        burnings.add(burningFire);
    }
}
