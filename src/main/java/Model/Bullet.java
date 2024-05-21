package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Bullet extends Rectangle {

    public static int sizeX = 166 / 10;
    public static int sizeY = 62 / 10;

    private static double speed = 5;
    private double speedX;
    private double speedY;

    public Bullet(int x, int y, double dir) {
        super(sizeX, sizeY);
        setX(x);
        setY(y);
        this.speedX = speed * Math.cos(dir);
        this.speedY = speed * Math.sin(dir);
        setFill(new ImagePattern(new Image(Bullet.class.getResource("/Images/Icons/bullet.png").toString())));
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
        for(Obstacle obstacle : Game.getCurrentGame().getAllObstaclesCopy()){
            if(getBoundsInParent().intersects(obstacle.getBoundsInParent())){
                obstacle.removeObject();
                this.remove();
                Game.getCurrentGame().increaseKills(obstacle.getScore());
                return;
            }
        }
    }

    private void remove() {
        Game.getCurrentGame().getBullets().remove(this);
        Game.getCurrentGame().getPane().getChildren().remove(this);
    }


}
