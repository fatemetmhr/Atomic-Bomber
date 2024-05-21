package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Model.*;

public class GameController {




    public static void setGameSettings(Pane root) {
        Game game = new Game(User.getLoggedInUser(), root);
        Game.setCurrentGame(game);

        Timeline planeTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> passTime()));
        planeTimeline.setCycleCount(Timeline.INDEFINITE);
        planeTimeline.play();


    }

    private static void passTime() {
        Game.getCurrentGame().getPlane().passTime();
        for (Bullet bullet : Game.getCurrentGame().getBullets()) {
            bullet.passTime();
        }
    }

    public static void keyPressed(KeyCode code) {
        Plane plane = Game.getCurrentGame().getPlane();
        if (code == ApplicationController.getDownKeyCode()) {
            plane.makeDirCloseTo(Math.PI / 2);
        } else if (code == ApplicationController.getUpKeyCode()) {
            plane.makeDirCloseTo(3 * Math.PI / 2);
        } else if (code == ApplicationController.getLeftKeyCode()) {
            plane.makeDirCloseTo(Math.PI);
        } else if (code == ApplicationController.getRightKeyCode()) {
            plane.makeDirCloseTo(0);
        } else if(code == KeyCode.SPACE){
            plane.shoot();
        }

    }
}

