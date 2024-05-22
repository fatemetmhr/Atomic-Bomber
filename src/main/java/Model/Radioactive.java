package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Radioactive extends Shot {

    public static int sizeX = 302 / 6;
    public static int sizeY = 141 / 6;
    private static double destroyingBoardX = 200;
    private static double destroyingBoardY = 20;

    public Radioactive(int x, int y, double dir) {
        super(x, y, dir, sizeX, sizeY);
        setFill(new ImagePattern(new Image(Bullet.class.getResource("/Images/Icons/radioactive.png").toString())));
    }

    @Override
    protected boolean willDestroy(Obstacle obstacle) {
        return Math.abs(getX() - obstacle.getX()) < destroyingBoardX && Math.abs(getY() - obstacle.getY()) < destroyingBoardY;
    }

    @Override
    protected void remove() {
        super.remove();
        Explosion explosion = new Explosion((int) getX(), (int) getY() - 100, Game.getCurrentGame());
    }
}
