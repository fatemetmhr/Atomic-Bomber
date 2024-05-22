package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Truck extends Obstacle {

    static double speedScale = 0.5;

    public Truck(Game game) {
        super((int) (450 / 3), (int) (360 / 3), 0, 700,
                (new Random().nextInt(2) == 0 ? 1 : -1) * speedScale, game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/truck.png").toString())));
        if (speed > 0)
            setScaleX(-1);
        score = 3;
    }

    @Override
    protected void addBurnings() {
        BurningFire burningFire = new BurningFire((int) getX(), (int) getY(), game);
        burnings.add(burningFire);
        burningFire = new BurningFire((int) getX() + 50, (int) getY() + 20, game);
        burnings.add(burningFire);
    }
}
