package Model;

import Controller.ApplicationController;
import Controller.GameController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Mig extends Rectangle {

    private static int sizeX = 890 / 8;
    private static int sizeY = 325 / 8;
    private int speed = 3;
    private Game game;
    private double shootingBoard = 250;

    public Mig(Game game) {
        super(sizeX, sizeY);
        this.game = game;
        Random random = new Random();
        speed *= random.nextInt(2) == 0 ? 1 : -1;
        setX(-2 * sizeX);
        if (speed < 0) {
            setX(1291 + sizeX);
            setScaleX(-1);
        }
        setY(100);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Icons/mig.png").toString())));
        game.getAllMigs().add(this);
        game.getPane().getChildren().add(this);
        shootingBoard *= ApplicationController.getGameDifficulty();
    }

    public void move() {
        if (GameController.isGameFreezed())
            return;
        setX(getX() + speed);
    }

    public void passTime() {
        move();
        if (getX() > 1291 + sizeX || getX() < -2 * sizeX)
            remove();
        checkForShooting();
    }

    public void checkForShooting() {
        if (GameController.isGameFreezed())
            return;
        Plane plane = game.getPlane();
        double distance = Math.sqrt(Math.pow(plane.getX() - getX(), 2) + Math.pow(plane.getY() - getY(), 2));
        if (distance < shootingBoard && game.isAnyTankBulletInFrame(this) == false)
            shoot();
    }

    public void remove() {
        game.getAllMigs().remove(this);
        game.getPane().getChildren().remove(this);
        game.resetMigTimer();
    }

    public void shoot() {
        new ObstacleBullet((int) getX() + sizeX / 2, (int) getY() + sizeY / 2, speed < 0 ? Math.PI : 0, this);
    }

}
