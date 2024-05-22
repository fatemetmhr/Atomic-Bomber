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
    }

    private void checkForCollision() {
        Game game = Game.getCurrentGame();
        for(Obstacle obstacle : game.getAllObstaclesCopy()){
            if(getBoundsInParent().intersects(obstacle.getBoundsInParent())){
                obstacle.removeObject();
                this.remove();
                game.increaseKills(obstacle.getScore());
                game.checkForIncreasedIce(obstacle);
                return;
            }
        }
    }

    private void remove() {
        Game.getCurrentGame().getShots().remove(this);
        Game.getCurrentGame().getPane().getChildren().remove(this);
    }
}
