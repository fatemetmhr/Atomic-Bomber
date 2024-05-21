package Model;

import Controller.ApplicationController;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.Random;

public class Tank extends Obstacle{

    static double speedScale = 0.2;

    public Tank(Game game){
        super((int)(1170 / 6), (int)(476 / 6), 0, 700,
                (new Random().nextInt(2) == 0 ? 1 : -1) * speedScale * ApplicationController.getGameDifficulty(), game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/tank.png").toString())));
        if(speed < 0)
            setScaleX(-1);
        score = 4;
    }
}
