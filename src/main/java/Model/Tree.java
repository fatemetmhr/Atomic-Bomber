package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tree extends Obstacle {

    public Tree(Game game, int x, int y) {
        super((int) (808 / 5), (int) (468 / 5), x, y, 0, game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/tree.png").toString())));
        score = 0;
    }

    @Override
    protected void addBurnings() {
        BurningFire burningFire = new BurningFire((int) getX() + 30, (int) getY() + 20, game);
        burnings.add(burningFire);
        burningFire = new BurningFire((int) getX() + 5, (int) getY() - 50, game);
        burnings.add(burningFire);
    }
}
