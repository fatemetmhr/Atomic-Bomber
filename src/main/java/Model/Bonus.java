package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bonus extends Rectangle {
    private final boolean isCluster;
    private double speed = 0.7;

    public Bonus(int x, int y, boolean isCluster) {
        super(isCluster ? 1920 / 40 : 682 / 14, isCluster ? 1920 / 40 : 691 / 14);
        this.isCluster = isCluster;
        setX(x);
        setY(y);
        Game.getCurrentGame().getBonuses().add(this);
        Game.getCurrentGame().getPane().getChildren().add(this);
        Random random = new Random();
        if (random.nextInt(2) == 0)
            speed *= -1;
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Icons/" + (isCluster ? "cluster" : "radioactive") + "_bonus.png").toString())));
    }

    public boolean isCluster() {
        return isCluster;
    }

    private void move() {
        setY(getY() - Math.abs(speed));
        setX(getX() - 2 * speed);
    }

    public void passTime() {
        move();
        if (getY() < -200)
            remove();
        if (getX() < 0 || getX() > 1291)
            speed *= -1;
        checkForCollision();
    }

    private void checkForCollision() {
        Game game = Game.getCurrentGame();
        Plane plane = game.getPlane();
        if (getBoundsInParent().intersects(plane.getBoundsInParent())) {
            if (isCluster)
                game.setRemainedClusters(game.getRemainedClusters() + 1);
            else
                game.setRemainedRadioactive(game.getRemainedRadioactive() + 1);
            remove();
        }
    }

    public void remove() {
        Game.getCurrentGame().getBonuses().remove(this);
        Game.getCurrentGame().getPane().getChildren().remove(this);
    }
}
