package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import Model.*;

import java.util.Random;

public class GameController {




    public static void setGameSettings(Pane root) {
        Game game = new Game(User.getLoggedInUser(), root);
        Game.setCurrentGame(game);

        Timeline planeTimeline = new Timeline(new KeyFrame(Duration.millis(10), e -> passTime()));
        planeTimeline.setCycleCount(Timeline.INDEFINITE);
        planeTimeline.play();


    }

    private static void passTime() {
        if(!Game.getCurrentGame().hasAnyTank())
            Game.getCurrentGame().addTank();
        Game.getCurrentGame().getPlane().passTime();
        for (Bullet bullet : Game.getCurrentGame().getBulletsCopy()) {
            bullet.passTime();
        }
        try{
            for (Obstacle obstacle : Game.getCurrentGame().getAllObstaclesCopy()) {
                obstacle.passTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println(e.getMessage());
            System.out.println(Game.getCurrentGame().getAllObstacles().size());
        }
    }

    public static void keyPressed(KeyCode code) {
        Game game = Game.getCurrentGame();
        Plane plane = game.getPlane();
        if (code == ApplicationController.getDownKeyCode()) {
            game.setDownDown(true);
        } else if (code == ApplicationController.getUpKeyCode()) {
            game.setUpKey(true);
        } else if (code == ApplicationController.getLeftKeyCode()) {
            game.setLeftKey(true);
        } else if (code == ApplicationController.getRightKeyCode()) {
            game.setRightKey(true);
        } else if(code == KeyCode.SPACE){
            plane.shoot();
        } else if(code == KeyCode.T){
            game.addTank();
        }

    }

    public static void keyReleased(KeyCode code) {
        Game game = Game.getCurrentGame();
        Plane plane = game.getPlane();
        if (code == ApplicationController.getDownKeyCode()) {
            game.setDownDown(false);
        } else if (code == ApplicationController.getUpKeyCode()) {
            game.setUpKey(false);
        } else if (code == ApplicationController.getLeftKeyCode()) {
            game.setLeftKey(false);
        } else if (code == ApplicationController.getRightKeyCode()) {
            game.setRightKey(false);
        }
    }
}

