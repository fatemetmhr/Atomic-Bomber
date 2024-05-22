package Model;

import javafx.scene.shape.Rectangle;

public abstract class Shot extends Rectangle {

    protected double speed = 5;
    protected double speedX;
    protected double speedY;

    public Shot(int x, int y, double dir, int sizeX, int sizeY){
        super(sizeX, sizeY);
        setX(x);
        setY(y);
        this.speedX = speed * Math.cos(dir);
        this.speedY = speed * Math.sin(dir);
    }

    void move(){
        setX(getX() + speedX);
        setY(getY() + speedY);
    }

    public void passTime(){
        move();
        speedY += 0.1;
        setRotate(Math.toDegrees(Math.atan2(speedY, speedX)));
        checkForCollision();
        if(getY() > 800)
            remove();
    }

    private void checkForCollision() {
        Game game = Game.getCurrentGame();
        for(Obstacle obstacle : game.getAllObstaclesCopy()){
            if(willDestroy(obstacle)){
                obstacle.removeObject();
                game.checkForIncreasedIce(obstacle);
                game.increaseKills(obstacle.getScore());
                obstacle.getBonus();
                if(this instanceof Bullet){
                    this.remove();
                    return;
                }
            }
        }
    }

    private void remove() {
        Game.getCurrentGame().getShots().remove(this);
        Game.getCurrentGame().getPane().getChildren().remove(this);
    }

    protected abstract boolean willDestroy(Obstacle obstacle);
}
