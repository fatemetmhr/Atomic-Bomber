package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Building extends Obstacle{

    public Building(Game game, int x, int y){
        super((int)(582 / 6), (int)(776 / 6), x, y, 0 , game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/building.png").toString())));
        score = 1;
    }

    @Override
    public void getBonus() {
        Bonus bonus = new Bonus((int)getX(), (int)getY(), false);
    }

}
