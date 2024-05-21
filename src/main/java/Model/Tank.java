package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Tank extends Obstacle{

    static double speedScale = 0.2;

    public Tank(Game game){
        super((int)(1170 / 4.6), (int)(476 / 4.6), (new Random().nextInt(2) == 0 ? 1 : -1) * speedScale , game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/tank.png").toString())));
        if(speed < 0)
            setScaleX(-1);
    }
}
