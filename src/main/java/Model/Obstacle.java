package Model;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public abstract class Obstacle extends Rectangle {

    protected double speed;
    protected int sizeX;
    protected int sizeY;
    protected Game game;
    protected int score;
    private boolean planeCollision = false;
    private boolean isBurning = false;
    private Timer burningTimer;
    protected ArrayList<BurningFire> burnings = new ArrayList<>();

    public Obstacle(int sizeX, int sizeY, int x, int y, double speed, Game game) {
        super(sizeX, sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.speed = speed;
        this.game = game;
        if (speed > 0)
            x = -sizeX;
        if (speed < 0)
            x = 1291;
        setX(x);
        setY(y);
        game.getAllObstacles().add(this);
        game.getPane().getChildren().add(this);
    }

    public void move() {
        setX(getX() + speed);
    }

    public void passTime() {
        move();
        if (getX() < -sizeX)
            removeObject();
        if (getX() > 1291)
            removeObject();
    }

    public void removeObject() {
        if (isBurning)
            return;
        game.getAllObstacles().remove(this);
        burningTimer = new Timer(1);
        addBurnings();
        game.getBurningObstacles().add(this);
        isBurning = true;
    }

    protected abstract void addBurnings();

    public void doneWithBurning() {
        game.getAllObstacles().remove(this);
        game.getBurningObstacles().remove(this);
        game.getPane().getChildren().remove(this);
        for (BurningFire burningFire : burnings)
            burningFire.remove();
        burnings.clear();
    }

    public int getScore() {
        return score;
    }

    public void getBonus() {
        return;
    }

    public void setPlaneCollision(boolean b) {
        planeCollision = b;
    }

    public boolean getPlaneCollision() {
        return planeCollision;
    }

    public int getBurningTime() {
        return burningTimer.getTimeCounter();
    }
}
