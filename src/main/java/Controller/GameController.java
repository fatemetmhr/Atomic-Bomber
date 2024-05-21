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
        Game game = Game.getCurrentGame();
        if(!game.isAnyMovingObstacleInFrame()) {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if(randomInt == 0 && game.numberOfTanks() < 3)
                game.addTank();
            if(randomInt == 1 && game.numberOfTrucks() < 2)
                game.addTruck();
        }
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
        }
        if (code == ApplicationController.getUpKeyCode()) {
            game.setUpKey(true);
        }
        if (code == ApplicationController.getLeftKeyCode()) {
            game.setLeftKey(true);
        }
        if (code == ApplicationController.getRightKeyCode()) {
            game.setRightKey(true);
        }
        if(code == KeyCode.SPACE){
            plane.shoot();
        }
        if(code == KeyCode.T){
            game.addTank();
        }

    }

    public static void keyReleased(KeyCode code) {
        Game game = Game.getCurrentGame();
        Plane plane = game.getPlane();
        if (code == ApplicationController.getDownKeyCode()) {
            game.setDownDown(false);
        }
        if (code == ApplicationController.getUpKeyCode()) {
            game.setUpKey(false);
        }
        if (code == ApplicationController.getLeftKeyCode()) {
            game.setLeftKey(false);
        }
        if (code == ApplicationController.getRightKeyCode()) {
            game.setRightKey(false);
        }
    }
}

