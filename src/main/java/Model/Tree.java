package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class Tree extends Obstacle{

    public Tree(Game game, int x, int y) {
        super((int) (808 / 5), (int) (468 / 5), x, y, 0, game);
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Obstacle/tree.png").toString())));
        score = 0;
    }
}
