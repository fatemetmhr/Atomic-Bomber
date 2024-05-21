package Model;

import javafx.scene.shape.Rectangle;

public abstract class Obstacle extends Rectangle {

    protected double speed;
    protected int sizeX;
    protected int sizeY;
    protected Game game;

    public Obstacle(int sizeX, int sizeY, double speed, Game game){
        super(sizeX, sizeY);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.speed = speed;
        this.game = game;
        int x = (int) (Math.random() * 1291);
        if(speed > 0)
            x = -sizeX;
        if(speed < 0)
            x = 1291;
        setX(x);
        setY(670);
        game.getAllObstacles().add(this);
    }

    public void move(){
        setX(getX() + speed);
    }

    public void passTime(){
        move();
        if(getX() < -sizeX)
            removeObject();
        if(getX() > 1291)
            removeObject();
    }

    public void removeObject() {
        game.getAllObstacles().remove(this);
        game.getPane().getChildren().remove(this);
    }
}
