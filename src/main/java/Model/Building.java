package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Building extends Obstacle {

    public Building(Game game, int x, int y) {
        super((int) (582 / 6), (int) (776 / 6), x, y, 0, game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/building.png").toString())));
        score = 1;
    }

    @Override
    public void getBonus() {
        Bonus bonus = new Bonus((int) getX(), (int) getY(), false);
    }

    @Override
    protected void addBurnings() {
        BurningFire burningFire = new BurningFire((int) getX(), (int) getY() - 10, game);
        burnings.add(burningFire);
        burningFire = new BurningFire((int) getX() + 30, (int) getY() + 50, game);
        burnings.add(burningFire);
    }

}
