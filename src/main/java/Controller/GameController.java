package Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import Model.*;

import java.util.Random;

public class GameController {


    public static Timeline gameTimeLine;
    private static int migTimePassing = 10;
    private static boolean migAlert = false;
    private static Timer alertTimer;
    private static boolean showWave = false;


    public static void setGameSettings(Pane root) {
        Game game = new Game(User.getLoggedInUser(), root);
        Game.setCurrentGame(game);

        gameTimeLine = new Timeline(new KeyFrame(Duration.millis(10), e -> passTime()));
        gameTimeLine.setCycleCount(Timeline.INDEFINITE);
        gameTimeLine.play();
    }

    public static void gameOver() {
        gameTimeLine.stop();
        View.MenuController.goToGameOver(View.Game.stage, ApplicationController.isWin());
    }

    private static void passTime() {
        Game game = Game.getCurrentGame();
        if (showWave)
            showNewWave(game.getWave());
        if (game == null || game.noTimePassing)
            return;
        if (!game.isAnyMovingObstacleInFrame()) {
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 0 && game.numberOfTanks() < 3)
                game.addTank();
            if (randomInt == 1 && game.numberOfTrucks() < 2)
                game.addTruck();
        }
        if (game.getWave() == 3 && game.getAllMigs().size() < 1) {
            int neededTime = (int) (migTimePassing * ((4.0 - ApplicationController.getGameDifficulty() + 1) / 4));
            if (game.getMigTimerTime() >= neededTime) {
                game.addMig();
            }
            GameController.showMigAlert(neededTime - game.getMigTimerTime());
        }
        game.getPlane().passTime();
        for (Shot shot : game.getShotsCopy()) {
            shot.passTime();
        }
        try {
            for (Obstacle obstacle : game.getAllObstaclesCopy()) {
                obstacle.passTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.toString());
            System.out.println(e.getMessage());
            System.out.println(game.getAllObstacles().size());
        }

        for (Bonus bonus : game.getBonusesCopy())
            bonus.passTime();

        for (Mig mig : game.getAllMigsCopy())
            mig.passTime();

        for (Obstacle obstacle : game.getCopyOfBurningObstacles()) {
            if (obstacle.getBurningTime() > 3)
                obstacle.doneWithBurning();
        }

        for (BurningAnimation burningAnimation : game.getCopyOfAllBurningAnimations()) {
            if (burningAnimation.getBurnTime() > 1)
                burningAnimation.remove();
        }

        if (game.isPlaneBurning && game.getPlane().getBurningTime() > 2) {
            game.gameOver(false);
            return;
        }

        View.GameController gameController = View.Game.gameController;
        gameController.showKillsAndAccuracy(game.getKills(), game.getAccuracy());

        if (game.getKills() >= 5 + (game.getWave() + 2) * 7 && game.isAnyStaticObstacleInFrame() == false) {
            game.nextWave();
        }
    }

    private static void showMigAlert(int left) {
        if (left == 0) {
            View.Game.gameController.showMigAlert(0);
            migAlert = false;
            return;
        }
        if (left > 3)
            return;
        if (!migAlert)
            alertTimer = new Timer(1.0 / 360);
        migAlert = true;
        View.Game.gameController.showMigAlert((Math.cos(Math.toRadians(alertTimer.getTimeCounter())) + 1) / 2);
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
        if (code == KeyCode.SPACE) {
            plane.shoot();
        }
        if (code == KeyCode.T) {
            game.addTank();
        }
        if (code == KeyCode.C) {
            plane.shootCluster();
        }
        if (code == KeyCode.CONTROL) {
            game.setRemainedClusters(game.getRemainedClusters() + 1);
        }
        if (code == KeyCode.R) {
            plane.shootRadioactive();
        }
        if (code == KeyCode.G) {
            game.setRemainedRadioactive(game.getRemainedRadioactive() + 1);
        }
        if (code == KeyCode.H) {
            plane.setHpToMax();
        }
        if (code == KeyCode.P) {
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
        for (int i = 0; i < 4; i++) {
            View.Game.gameController.changeHeart(i + 1, 2);
        }
    }

    public static void showNewWave(int wave) {
        View.GameController gameController = View.Game.gameController;
        gameController.showWave(wave);
        if (alertTimer == null)
            alertTimer = new Timer(1.0 / 360);
        if (alertTimer.getTimeCounter() > 3 * 360) {
            alertTimer = null;
            showWave = false;
            View.Game.gameController.showNewWaveAlert(wave, 0);
            return;
        }
        View.Game.gameController.showNewWaveAlert(wave, 1);
    }

    public static void setShowWave(boolean b) {
        showWave = b;
    }
}

