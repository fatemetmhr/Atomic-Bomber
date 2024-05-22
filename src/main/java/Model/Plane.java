package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle {

    static int sizeX = 890 / 8;
    static int sizeY = 325 / 8;
    static int maxSpeed = 5;
    static int minSpeed = 3;

    private double dir;
    private double speed;
    private Game game;
    private int bulletX = 2;
    private int bulletY = -20;
    private boolean isMirrored = false;
    private int hp;

    private Timer burningTimer;

    private Timer damegingTimer;
    Image image;

    public Plane(Game game) {
        super(sizeX, sizeY);
        setX(100);
        setY(150);
        dir = 0;
        speed = minSpeed;
        this.game = game;
        hp = 8;
        damegingTimer = new Timer(1);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Icons/plane.png").toString())));
    }


    public void move(double speed) {
        setX(getX() + speed * Math.cos(dir));
        setY(getY() + speed * Math.sin(dir));
    }

    public void passTime() {
        if (burningTimer != null) {
            return;
        }
        if (game.getDownDown()) {
            makeDirCloseTo(Math.PI / 2);
        } else if (game.getUpKey()) {
            makeDirCloseTo(3 * Math.PI / 2);
        } else if (game.getLeftKey()) {
            makeDirCloseTo(Math.PI);
        } else if (game.getRightKey()) {
            makeDirCloseTo(0);
        }
        move(speed);
        if (getX() < -sizeX)
            setX(1291);
        if (getX() > 1291)
            setX(-sizeX);
        if (getY() < 0)
            setDir(-dir);
        if (getY() > 849 - sizeY) {
            setDir(-dir);
            gotDamaged();
        }
        checkForCollision();
    }

    private void checkForCollision() {
        for (Obstacle obstacle : game.getAllObstaclesCopy()) {
            if (obstacle.intersects(this.getBoundsInLocal())) {
                if (!obstacle.getPlaneCollision())
                    gotDamaged();
                obstacle.setPlaneCollision(true);
            } else {
                obstacle.setPlaneCollision(false);
            }
        }
    }


    public void setDir(double v) {
        if (burningTimer != null)
            return;
        v += 10 * Math.PI;
        v %= 2 * Math.PI;
        if (Math.abs(v) < 0.0001 || Math.abs(v - 2 * Math.PI) < 0.0001)
            v = 0;
        if (Math.abs(v - Math.PI) < 0.0001)
            v = Math.PI;
        if (Math.abs(v - Math.PI / 2) < 0.0001)
            v = Math.PI / 2;
        if (Math.abs(v - 3 * Math.PI / 2) < 0.0001)
            v = 3 * Math.PI / 2;
        dir = v;
        if (v >= Math.PI / 2 && v <= 3 * Math.PI / 2) {
            setScaleY(-1);
            isMirrored = true;
        } else {
            setScaleY(1);
            isMirrored = false;
        }
        setRotate(Math.toDegrees(dir));
    }

    public double getDir() {
        return dir;
    }

    public void makeDirCloseTo(double v) {
        int scale = 180;
        if (dir == v) {
            speed++;
            speed = Math.min(speed, maxSpeed);
            return;
        }
        speed = minSpeed;
        if (getMinRotationDistance(dir, v) < getMinRotationDistance(v, dir))
            setDir(dir + Math.PI / scale);
        else
            setDir(dir - Math.PI / scale);
    }

    private double getMinRotationDistance(double dir1, double dir2) {
        if (dir1 <= dir2)
            return dir2 - dir1;
        return 2 * Math.PI - dir1 + dir2;
    }

    public void shoot() {
        Bullet bullet = new Bullet((int) (getX() + (sizeX / 2)), (int) (getY() + (sizeY / 2)), dir);
        game.increaseShoots();
    }

    public void shootCluster() {
        if (game.getRemainedClusters() == 0) {
            return;
        }
        for (int i = 0; i < 6; i++) {
            Bullet bullet = new Bullet((int) getX() + (sizeX / 2), (int) (getY() + (sizeY / 2)), dir + (isMirrored ? -1 : 1) * Math.PI / 6 * i);
        }
        game.increaseShoots();
        game.setRemainedClusters(game.getRemainedClusters() - 1);
    }

    public void shootRadioactive() {
        if (game.getRemainedRadioactive() == 0) {
            return;
        }
        Radioactive radioactive = new Radioactive((int) (getX() + (sizeX / 2)), (int) (getY() + (sizeY / 2)), dir);
        game.increaseShoots();
    }

    public void gotDamaged() {
        if (damegingTimer.getTimeCounter() > 1)
            damegingTimer = null;
        if (damegingTimer != null)
            return;
        damegingTimer = new Timer(1);
        Controller.GameController.reduceHp(hp);
        hp--;
        if (hp == 0) {
            BurningFire burningFire = new BurningFire((int) getX(), (int) getY() - 20, game);
            game.isPlaneBurning = true;
            burningTimer = new Timer(1);
            return;
        }
    }

    public int getBurningTime() {
        return burningTimer.getTimeCounter();
    }

    public void setHpToMax() {
        hp = 8;
        Controller.GameController.setHpToMax();
    }

    public void resetPosition() {
        setX(100);
        setY(150);
        setDir(0);
    }
}
