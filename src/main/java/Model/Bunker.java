package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Bunker extends Obstacle {

    public Bunker(Game game, int x, int y) {
        super((int) (466 / 3), (int) (186 / 3), x, y, 0, game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/bunker.png").toString())));
        score = 2;
    }

    @Override
    public void getBonus() {
        Bonus bonus = new Bonus((int) getX(), (int) getY(), true);
    }

    @Override
    protected void addBurnings() {
        BurningFire burningFire = new BurningFire((int) getX(), (int) getY() - 15, game);
        burnings.add(burningFire);
        burningFire = new BurningFire((int) getX() + 50, (int) getY() - 10, game);
        burnings.add(burningFire);
    }
}
