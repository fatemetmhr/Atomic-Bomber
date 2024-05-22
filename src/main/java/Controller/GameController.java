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


    public static Timeline gameTimeLine;


    public static void setGameSettings(Pane root) {
        Game game = new Game(User.getLoggedInUser(), root);
        Game.setCurrentGame(game);

        gameTimeLine = new Timeline(new KeyFrame(Duration.millis(10), e -> passTime()));
        gameTimeLine.setCycleCount(Timeline.INDEFINITE);
        gameTimeLine.play();
    }

    public static void gameOver(){
        gameTimeLine.stop();
    }

    private static void passTime() {
        Game game = Game.getCurrentGame();
        if(game == null || game.noTimePassing)
            return;
        if(!game.isAnyMovingObstacleInFrame()) {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if(randomInt == 0 && game.numberOfTanks() < 3)
                game.addTank();
            if(randomInt == 1 && game.numberOfTrucks() < 2)
                game.addTruck();
        }
        Game.getCurrentGame().getPlane().passTime();
        for (Shot shot : Game.getCurrentGame().getShotsCopy()) {
            shot.passTime();
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

        for(Bonus bonus : Game.getCurrentGame().getBonusesCopy()){
            bonus.passTime();
        }

        View.GameController gameController = View.Game.gameController;
        gameController.showKillsAndAccuracy(game.getKills(), game.getAccuracy());

        if(game.getKills() >= 5 + (game.getWave() + 2) * 7 && game.isAnyStaticObstacleInFrame() == false){
            game.nextWave();
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
        if(code == KeyCode.C){
            plane.shootCluster();
        }
        if(code == KeyCode.CONTROL){
            game.setRemainedClusters(game.getRemainedClusters() + 1);
        }
        if(code == KeyCode.R){
            plane.shootRadioactive();
        }
        if(code == KeyCode.G){
            game.setRemainedRadioactive(game.getRemainedRadioactive() + 1);
        }
        if(code == KeyCode.H){
            plane.setHpToMax();
        }
        if(code == KeyCode.P){
            game.nextWave();
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

    public static void showClusters(int remainedClusters) {
        View.Game.gameController.showClusters(remainedClusters);
    }

    public static void showRadioactive(int remainedRadioactive) {
        View.Game.gameController.showRadioactive(remainedRadioactive);
    }

    public static void reduceHp(int hp) {
        hp--;
        View.Game.gameController.changeHeart(hp / 2 + 1, hp % 2);
    }

    public static void setHpToMax() {
        for(int i = 0; i < 4; i++){
            View.Game.gameController.changeHeart(i + 1, 2);
        }
    }
}

