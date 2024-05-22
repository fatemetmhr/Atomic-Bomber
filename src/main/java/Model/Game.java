package Model;

import Controller.GameController;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Game {

    static Game currentGame = null;
    static ArrayList<Game> allGames = new ArrayList<>();
    public boolean isPlaneBurning = false;
    private User user;
    private Plane plane;
    private ArrayList<Shot> allShots = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Pane pane;
    private int wave = 1;
    private int kills = 0;
    private int successfulShots = 0;
    private boolean upKey = false;
    private boolean downDown = false;
    private boolean leftKey = false;
    private boolean rightKey = false;
    private int shoots = 0;
    private int ice;
    private int remainedClusters = 0;
    private int remainedRadioactive = 0;
    private ArrayList<Bonus> Bonuses = new ArrayList<>();
    public boolean noTimePassing = false;
    private ArrayList<Mig> allMigs = new ArrayList<>();
    private Timer migTimer;
    private ArrayList<Obstacle> burningObstacles = new ArrayList<>();
    private ArrayList<BurningAnimation> allBurningAnimations = new ArrayList<>();


    public Game(User user, Pane root) {
        this.user = user;
        allGames.add(this);
        this.pane = root;
        plane = new Plane(this);
        root.getChildren().add(plane);
        ice = 0;
        createWave();
        migTimer = new Timer(1);
    }

    private void createWave() {
        Controller.GameController.setShowWave(true);
        Controller.GameController.showNewWave(wave);
        Building building = new Building(this, 950, 600);
        building = new Building(this, 400, 550);
        Bunker bunker = new Bunker(this, 670, 650);
        Tree tree = new Tree(this, 30, 650);
        tree = new Tree(this, 1100, 630);
        tree = new Tree(this, 530, 650);
        if (wave == 3)
            migTimer.reset();
    }


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Game.currentGame = currentGame;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }


    public Pane getPane() {
        return pane;
    }

    public ArrayList<Obstacle> getAllObstacles() {
        return obstacles;
    }

    public void addTank() {
        Tank tank = new Tank(this);
    }

    public ArrayList<Shot> getShotsCopy() {
        return new ArrayList<>(allShots);
    }

    public ArrayList<Obstacle> getAllObstaclesCopy() {
        return new ArrayList<>(obstacles);
    }

    public int numberOfTanks() {
        int cnt = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Tank)
                cnt++;
        }
        return cnt;
    }

    public void increaseKills(int score) {
        kills += score;
    }

    public boolean getUpKey() {
        return upKey;
    }

    public void setUpKey(boolean upKey) {
        this.upKey = upKey;
    }

    public boolean getDownDown() {
        return downDown;
    }

    public void setDownDown(boolean downDown) {
        this.downDown = downDown;
    }

    public boolean getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(boolean leftKey) {
        this.leftKey = leftKey;
    }

    public boolean getRightKey() {
        return rightKey;
    }

    public void setRightKey(boolean rightKey) {
        this.rightKey = rightKey;
    }


    public int numberOfTrucks() {
        int cnt = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Truck)
                cnt++;
        }
        return cnt;
    }

    public void addTruck() {
        Truck truck = new Truck(this);
    }

    public boolean isAnyMovingObstacleInFrame() {
        for (Obstacle obstacle : obstacles)
            if (obstacle instanceof Truck || obstacle instanceof Tank) {
                if (obstacle.getX() < 200 || obstacle.getX() > 1101)
                    return true;
            }
        return false;
    }

    public int getKills() {
        return kills;
    }

    public double getAccuracy() {
        if (shoots == 0)
            return 100;
        return (double) ((successfulShots) * 10000 / shoots) / 100;
    }

    public void increaseShoots() {
        shoots++;
    }

    public void checkForIncreasedIce(Obstacle obstacle) {
        if (obstacle instanceof Truck || obstacle instanceof Tank || obstacle instanceof Building) {
            if (ice < 5) {
                ice++;
                View.Game.gameController.changeIce(5 - ice + 1, true);
            }
        }
    }

    public ArrayList<Shot> getShots() {
        return allShots;
    }

    public void setRemainedClusters(int remainedClusters) {
        this.remainedClusters = remainedClusters;
        Controller.GameController.showClusters(remainedClusters);
    }

    public int getRemainedClusters() {
        return remainedClusters;
    }

    public void setRemainedRadioactive(int remainedRadioactive) {
        this.remainedRadioactive = remainedRadioactive;
        Controller.GameController.showRadioactive(remainedRadioactive);
    }

    public int getRemainedRadioactive() {
        return remainedRadioactive;
    }

    public ArrayList<Bonus> getBonuses() {
        return Bonuses;
    }

    public ArrayList<Bonus> getBonusesCopy() {
        return new ArrayList<>(Bonuses);
    }

    public void gameOver(boolean isWin) {
        Controller.GameController.gameOver();
    }

    public boolean isAnyTankBulletInFrame(Rectangle rectangle) {
        for (Shot shot : allShots)
            if (shot instanceof ObstacleBullet && ((ObstacleBullet) shot).getOwner() == rectangle) {
                return true;
            }
        return false;
    }

    public void increaseSuccessfulShots() {
        successfulShots++;
    }

    public int getSuccessfulShots() {
        return successfulShots;
    }

    public int getWave() {
        return wave;
    }

    public boolean isAnyStaticObstacleInFrame() {
        for (Obstacle obstacle : obstacles)
            if (obstacle instanceof Building || obstacle instanceof Tree || obstacle instanceof Bunker)
                return true;
        return false;
    }

    public void nextWave() {
        if (wave == 3) {
            gameOver(true);
        }
        noTimePassing = true;
        removeLastWave();
        wave++;
        createWave();
        noTimePassing = false;
    }

    private void removeLastWave() {
        ArrayList<Obstacle> copyOfObstacles = new ArrayList<>(obstacles);
        for (Obstacle obstacle : copyOfObstacles)
            obstacle.doneWithBurning();
        for (Obstacle obstacle : getCopyOfBurningObstacles())
            obstacle.doneWithBurning();
        ArrayList<Shot> copyOfShots = new ArrayList<>(allShots);
        for (Shot shot : copyOfShots) {
            shot.remove();
        }
        upKey = false;
        downDown = false;
        leftKey = false;
        rightKey = false;
    }

    public ArrayList<Mig> getAllMigs() {
        return allMigs;
    }

    public void addMig() {
        Mig mig = new Mig(this);
    }

    public int getMigTimerTime() {
        if (migTimer == null)
            return -1;
        return migTimer.getTimeCounter();
    }

    public void resetMigTimer() {
        migTimer.reset();
    }

    public ArrayList<Mig> getAllMigsCopy() {
        return new ArrayList<>(allMigs);
    }

    public ArrayList<Obstacle> getBurningObstacles() {
        return burningObstacles;
    }

    public ArrayList<Obstacle> getCopyOfBurningObstacles() {
        return new ArrayList<>(burningObstacles);
    }

    public ArrayList<BurningAnimation> getAllBurningAnimations() {
        return allBurningAnimations;
    }

    public ArrayList<BurningAnimation> getCopyOfAllBurningAnimations() {
        return new ArrayList<>(allBurningAnimations);
    }

}
